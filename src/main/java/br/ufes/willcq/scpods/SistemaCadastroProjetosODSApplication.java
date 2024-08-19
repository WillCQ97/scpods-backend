package br.ufes.willcq.scpods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaCadastroProjetosODSApplication {

    public static void main( String[] args ) {
        SpringApplication.run( SistemaCadastroProjetosODSApplication.class, args );
    }

}
