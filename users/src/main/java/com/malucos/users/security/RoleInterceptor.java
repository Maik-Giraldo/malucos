package com.malucos.users.security;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // Verifica que el handler sea un método del controller y no cualquier otra cosa
        if (!(handler instanceof HandlerMethod method)) {
            // if(handler == null || !(handler instanceOf HandlerMethod))
            return true; // Si no es un endpoint no nos importa
        }

        // Busca la anotación @RequiresRole en el método
        RequiresRole annotation = method.getMethodAnnotation(RequiresRole.class);

        if (annotation == null) {
            return true; // Tampoco nos importa
        }

        Object rol = request.getAttribute("rolId");

        if (!(rol instanceof Long rolId)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"User is not authenticated\"}");
            return false; // mochamos la petición, no alcanza a llegar al controller
        }

        // Verifica si el rol esta dentro de los roles permitidos
        Boolean hasRole = Arrays.stream(annotation.value()).anyMatch(role -> role.getId() == rolId);

        if (!hasRole) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"The user does not have permission to perform this action. \"}");
            return false;
        }

        // Si pasa todas las validaciones, permite continual al controller
        return true;
    }
}
