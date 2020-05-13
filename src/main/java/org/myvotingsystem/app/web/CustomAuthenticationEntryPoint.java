package org.myvotingsystem.app.web;

import org.myvotingsystem.app.util.JsonUtil;
import org.myvotingsystem.app.util.ValidationUtil;
import org.myvotingsystem.app.util.exception.ExceptionInfo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.writeValue(
                new ExceptionInfo(request.getRequestURL(), ValidationUtil.getRootCause(authException))));
        writer.flush();
        writer.close();
    }
}
