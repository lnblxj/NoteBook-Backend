package top.sboxm.notebook.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

/**
 * 请求日志（乱搞的）
 */
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        long startTime = System.currentTimeMillis();

        try {
            logRequest(requestWrapper);
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            logResponse(responseWrapper, System.currentTimeMillis() - startTime);

            responseWrapper.copyBodyToResponse();
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String queryString = request.getQueryString();
        String url = request.getRequestURL() + (queryString != null ? "?" + queryString : "");
        
        logger.info("Request: {} {} (Content-Type: {})", 
                request.getMethod(), 
                url,
                request.getContentType());

        Collections.list(request.getHeaderNames()).forEach(headerName -> 
                logger.debug("Request Header: {}: {}", headerName, request.getHeader(headerName)));
    }

    private void logResponse(ContentCachingResponseWrapper response, long timeElapsed) {

        byte[] content = response.getContentAsByteArray();
        String contentAsString = new String(content, StandardCharsets.UTF_8);
        
        logger.info("Response: {} ({} bytes) in {} ms", 
                response.getStatus(), 
                content.length,
                timeElapsed);

        response.getHeaderNames().forEach(headerName -> 
                logger.debug("Response Header: {}: {}", headerName, response.getHeader(headerName)));

        if (content.length > 0 && content.length < 1000) {
            logger.debug("Response Body: {}", contentAsString);
        }
    }
}