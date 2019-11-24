package fi.tamk.tiko.joonass.jotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("Jotel-Identity")
                .allowedHeaders("Jotel-Identity", "OPTIONS", "Content-Type")
                .allowedMethods("OPTIONS", "GET", "POST", "DELETE")
                .allowCredentials(true);
    }

}