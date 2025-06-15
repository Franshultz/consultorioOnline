package ar.com.cdt.formacion.consultorioOnline.models;

public class ConsultorioDia {

    int  idConsultorio, idDia;

    public ConsultorioDia(int idConsultorio, int idDia) {
        this.idConsultorio = idConsultorio;
        this.idDia = idDia;
    }

    public int getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }
}
