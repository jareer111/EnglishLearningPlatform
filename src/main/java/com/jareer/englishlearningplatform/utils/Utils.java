package com.jareer.englishlearningplatform.utils;

import com.jareer.englishlearningplatform.domains.Users;
import com.jareer.englishlearningplatform.enums.Roles;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Utils {
    public static final String USER_PAGE = "/user";
    public static final String ADMIN_PAGE = "/admin-page";
    public static final String TEACHER_PAGE = "/teacher";

    // redirect to a page according to the role
    public static String redirectByRole(Users user) {
        Roles role = user.getRole();
        switch (role) {
            case ADMIN:
                return ADMIN_PAGE;
            case TEACHER:
                return TEACHER_PAGE;
            case USER:
                return USER_PAGE;
            default:
                return null;
        }
    }

    // check the cookie if it contains the remember_me key
    public static boolean checkCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember_me")) {
                    return true;
                }
            }
        }
        return false;
    }

    // return the value of a cookie that contains the remember_me key
    public static String getCookieValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("remember_me")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void removeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember_me")) {
                cookie.setMaxAge(0);
            }
        }
    }
}
