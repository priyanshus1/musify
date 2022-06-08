package com.shukla.musify.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebFilter
@Component
public class MDCRequestFilter extends HttpFilter {
    public static final String X_CORRELATION_ID = "X-CorrelationId";
    private static final Logger LOG = LoggerFactory.getLogger(MDCRequestFilter.class);

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            MDC.put(X_CORRELATION_ID, getOrCreateCorrelationId(request));
            LOG.info("Processing request");
            response.setHeader(X_CORRELATION_ID, MDC.get(X_CORRELATION_ID));
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(X_CORRELATION_ID);
        }
    }

    private String getOrCreateCorrelationId(HttpServletRequest request) {
        String correlationId = request.getHeader(X_CORRELATION_ID);
        if (correlationId == null) {
            LOG.info("X-CorrelationId missing in the header");
            return UUID.randomUUID().toString();
        }
        return correlationId;
    }
}