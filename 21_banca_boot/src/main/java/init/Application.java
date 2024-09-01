package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableJpaRepositories(basePackages ={"dao"})
@EntityScan(basePackages = {"entities"})
@SpringBootApplication(scanBasePackages = {"controller","service","utilidades"})
public class Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		//PRueba sourcetree
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/toMenu").setViewName("menu");
		registry.addViewController("/toMovimientoCuenta").setViewName("movimiento-cuenta");
		registry.addViewController("/").setViewName("inicio");
		registry.addViewController("/toInicio").setViewName("inicio");
		registry.addViewController("/toTitulares").setViewName("titulares");
		registry.addViewController("/toIngresar").setViewName("ingresar");
		registry.addViewController("/toSacar").setViewName("sacar");
		registry.addViewController("/toTransferencia").setViewName("transferencia");
		registry.addViewController("/toError").setViewName("error");
		
		WebMvcConfigurer.super.addViewControllers(registry);
		
		
	}

}
