package com.eventapp.wishlistservice.filter;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtWishlistFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpres = (HttpServletResponse) response;

        httpres.setHeader("Access-Control-Allow-Origin", "*");
        httpres.setHeader("Access-Control-Allow-Headers", "*");
        httpres.setHeader("Access-Control-Allow-Credentials", "true");
        httpres.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");

        // to handle preflight request for the first time which is raised by web-browser

        if (httpreq.getMethod().equals(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(httpreq, httpres);
        } else {
            String headerInfo = httpreq.getHeader("Authorization");
            if ((headerInfo == null) || (!headerInfo.startsWith("Bearer"))) {
                throw new ServletException("JWT Token is missing");
            }
            String token = headerInfo.substring(7);
            try {
                JwtParser jwtparser = Jwts.parser().setSigningKey("secret");
                Jwt jwtObj = jwtparser.parse(token);
                Claims claimObj = (Claims) jwtObj.getBody();
                HttpSession session = httpreq.getSession();
                session.setAttribute("username", claimObj.getSubject());
            } catch (SignatureException e) {
                throw new ServletException("Invalid signature / token ");
            } catch (MalformedJwtException e) {
                throw new ServletException("Someone illegally modified token");
            }
            filterChain.doFilter(httpreq, httpres);
        }
    }
}
