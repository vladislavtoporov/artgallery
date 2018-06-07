package ru.kpfu.itis.artgallery.filter;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.artgallery.authentication.JwtTokenAuthentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
public class JwtTokenAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        JwtTokenAuthentication authentication;

        if (token == null) {
            authentication = new JwtTokenAuthentication(null);
            authentication.setAuthenticated(false);
        } else {
            authentication = new JwtTokenAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
