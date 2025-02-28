package xyz.xpto.gerenciamento.api.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// Configuração de Content Negotiation
	@Override
	public void configureContentNegotiation(@NonNull ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}

	// Configuração de CORS
	@Override
	public void addCorsMappings(@NonNull CorsRegistry corsRegistry) {
		// TODO: Configurar o CORS para o ambiente de produção
		corsRegistry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

}
