package ar.com.cdt.formacion.consultorioOnline.util;

import ar.com.cdt.formacion.consultorioOnline.repositories.RepositoryPaciente;
import ar.com.cdt.formacion.consultorioOnline.service.ServiceMedico;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.UUID;

@RestController
public class GoogleCalendarMeetService {

    @Autowired
    private RepositoryPaciente repositoryPaciente;

    private static final String APPLICATION_NAME = "consultorioOnline";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FILE_PATH = "/client_google_calendar_meet.json";

    private static final String REDIRECT_URI = "http://localhost:8080/oauth2callback";

    private static final String USER_IDENTIFIER_KEY = "user";

    private GoogleAuthorizationCodeFlow flow;
    private NetHttpTransport httpTransport;

    public GoogleCalendarMeetService() throws Exception {
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = GoogleCalendarMeetService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new RuntimeException("No se encontró " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, Collections.singleton(CalendarScopes.CALENDAR_EVENTS))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();
    }

    // 1) Método para obtener la URL de autorización para enviar al usuario
    public String obtenerUrlAutorizacion(int fkPaciente) {
        return flow.newAuthorizationUrl()
                .setRedirectUri(REDIRECT_URI)
                .setAccessType("offline")
                .set("prompt", "consent")
                .setState(String.valueOf(fkPaciente))  // Pasás el id para usarlo luego
                .build();
    }

    // 2) Método para intercambiar el código por credenciales
    public com.google.api.client.auth.oauth2.Credential intercambiarCodigoPorCredenciales(String code) throws Exception {
        // Intercambia el código por el token
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code)
                .setRedirectUri(REDIRECT_URI)
                .execute();

        // Crea el Credential y setea el token
        return flow.createAndStoreCredential(tokenResponse, "user");
    }

    // 3) Crear evento con enlace Meet
    public String crearEventoConMeet(Credential cred, String resumen, ZonedDateTime inicio, ZonedDateTime fin) throws Exception {
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, cred)
                .setApplicationName(APPLICATION_NAME)
                .build();

        Event event = new Event()
                .setSummary(resumen)
                .setStart(new EventDateTime().setDateTime(new DateTime(inicio.toInstant().toEpochMilli())))
                .setEnd(new EventDateTime().setDateTime(new DateTime(fin.toInstant().toEpochMilli())))
                .setConferenceData(new ConferenceData()
                        .setCreateRequest(new CreateConferenceRequest()
                                .setRequestId(UUID.randomUUID().toString())));

        Event createdEvent = service.events().insert("primary", event)
                .setConferenceDataVersion(1)
                .execute();

        if (createdEvent.getConferenceData() != null &&
                createdEvent.getConferenceData().getEntryPoints() != null &&
                !createdEvent.getConferenceData().getEntryPoints().isEmpty()) {
            return createdEvent.getConferenceData().getEntryPoints().get(0).getUri();
        } else {
            return "No se pudo generar el enlace de Meet.";
        }
    }


    // --- Ejemplo de controlador REST para manejar el flujo OAuth ---

    @GetMapping("/authurl")
    public void redirectToGoogleAuth(@RequestParam("fkPaciente") int fkPaciente, HttpServletResponse response) throws Exception {
        String url = obtenerUrlAutorizacion(fkPaciente);
        response.sendRedirect(url);
    }


    @GetMapping("/oauth2callback")
    public String oauth2callback(@RequestParam("code") String code, @RequestParam("state") String state) {
        try {
            int fkPaciente = Integer.parseInt(state);

            Credential cred = intercambiarCodigoPorCredenciales(code);

            String refreshToken = cred.getRefreshToken();
            String accessToken = cred.getAccessToken();

            repositoryPaciente.guardarRefreshToken(fkPaciente, refreshToken);
            repositoryPaciente.guardarAccessToken(fkPaciente, accessToken);

            return "Autorización exitosa para paciente id: " + fkPaciente;

        } catch (Exception e) {
            // Imprime toda la pila de errores en consola
            e.printStackTrace();

            // Devuelve el error completo como string (para que lo veas en el navegador)
            StringBuilder sb = new StringBuilder();
            sb.append("Error durante la autorización:\n");
            sb.append(e.toString()).append("\n");
            for (StackTraceElement ste : e.getStackTrace()) {
                sb.append("\tat ").append(ste.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public Credential crearCredentialConRefreshToken(String refreshToken) throws Exception {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);

        return flow.createAndStoreCredential(tokenResponse, "user");
    }

    @GetMapping("/crear-evento-meet")
    public String crearEventoMeet(@RequestParam("fkPaciente") int fkPaciente) {
        try {
            // 1. Obtener el refresh token guardado para el paciente
            String refreshToken = repositoryPaciente.obtenerRefreshToken(fkPaciente);
            if (refreshToken == null) {
                return "El paciente no tiene un refresh token guardado. Primero debe autorizar la app.";
            }

            // 2. Crear la credencial con el refresh token
            Credential cred = crearCredentialConRefreshToken(refreshToken);

            // 3. Crear el evento (podés reemplazar las fechas por dinámicas si querés)
            ZonedDateTime inicio = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")).plusDays(1).withHour(10).withMinute(0);
            ZonedDateTime fin = inicio.plusMinutes(30);

            String meetLink = this.crearEventoConMeet(cred, "Consulta médica", inicio, fin);

            return "Evento creado exitosamente. Enlace de Meet: " + meetLink;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error al crear el evento: " + e.getMessage();
        }
    }
}
