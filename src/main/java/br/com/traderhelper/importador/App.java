package br.com.traderhelper.importador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        LocalDateTime horaInicial = LocalDateTime.now();

        log.info("Início da execução");
        SpringApplication.run(App.class, args);

        LocalDateTime horaFinal = LocalDateTime.now();

        log.info("Finalizado processo batch.");
        log.info("Tempo total: {} Segundos", ChronoUnit.SECONDS.between(horaInicial, horaFinal));
    }

}
