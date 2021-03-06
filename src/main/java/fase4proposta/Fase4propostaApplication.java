package fase4proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Fase4propostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fase4propostaApplication.class, args);
	}

}
