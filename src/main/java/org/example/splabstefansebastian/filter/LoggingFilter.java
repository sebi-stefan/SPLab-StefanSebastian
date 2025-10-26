package org.example.splabstefansebastian.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(1)
@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("Request recieved for {}", httpRequest.getRequestURI());

        filterChain.doFilter(request, response);

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        log.info("Response status: {}", httpResponse.getStatus());
    }
}