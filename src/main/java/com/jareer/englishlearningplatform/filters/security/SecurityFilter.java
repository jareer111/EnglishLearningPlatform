package com.jareer.englishlearningplatform.filters.security;

import com.jareer.englishlearningplatform.utils.AES;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Log
@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    private static final List<String> ALLOWED_PATHS = List.of(
            "/",
            "/login",
            "/register",
            "/resources/.+",
            "/views/.+",
            "/fragments/.+",
//            "/user",
            "/questions/.+",
            "/vocabulary/.+",
            "/uploads/.+"

    );
    private static final Predicate<String> IS_ALLOWED_PATH = path -> ALLOWED_PATHS.stream().anyMatch(path::matches);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (IS_ALLOWED_PATH.test(path)) {
            chain.doFilter(request, response);
        } else {
            Cookie[] cookies = req.getCookies();
            if (Objects.isNull(cookies)) {
                res.sendRedirect(req.getContextPath() + "/login");
            } else {
                Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals("remember_me"))
                        .findFirst()
                        .ifPresentOrElse(
                                cookie -> {
                                    HttpSession session = req.getSession();
                                    int userId = Integer.parseInt(AES.decrypt(cookie.getValue()));
                                    session.setAttribute("user_id", userId);
                                    try {
                                        chain.doFilter(request, response);
                                    } catch (ServletException | IOException e) {
                                        e.printStackTrace();
                                    }
                                },
                                () -> {
                                    try {
                                        res.sendRedirect("/login?next=" + requestURI); // TODO: 2/17/2023
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
            }
        }
    }
}
