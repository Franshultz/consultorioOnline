package ar.com.cdt.formacion.consultorioOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ConsultorioOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultorioOnlineApplication.class, args);
    }
}
