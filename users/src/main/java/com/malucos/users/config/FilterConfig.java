package com.malucos.users.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.malucos.users.filter.JwtValidationFilter;

@Configuration // Fábrica de beans
public class FilterConfig {

    @Bean
    FilterRegistrationBean<JwtValidationFilter> jwtFilter(JwtValidationFilter jwtValidationFilter) {
        // Crear un contenedor de Registro del bean para el filtro
        FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>();

        // Es decirle a spring que este es el filtro con el que quiero que trabaje
        registrationBean.setFilter(jwtValidationFilter);

        // Definir el alcance de este filtro, quiero que revise todas las peticiones que entren a mi api
        registrationBean.addUrlPatterns("/*");

        // Establecemos la prioridad de ejecución de los filtros
        // Este filtro se va a ejecutar antes que los otros filtros internos
        registrationBean.setOrder(1);

        // Retornamos el registro configurado para que spring lo guarde en su contexto
        return registrationBean;
    }
}
