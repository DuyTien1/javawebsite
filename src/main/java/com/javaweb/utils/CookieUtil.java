package com.javaweb.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void getCookie(HttpServletRequest request) {
        Cookie[] cookieArr = request.getCookies();
        if (cookieArr != null) {
            for (Cookie v : cookieArr) {
                if (v.getName().equals("username")) {
                    request.setAttribute("username", v.getValue());
                }
                if (v.getName().equals("password")) {
                    request.setAttribute("password", v.getValue());
                }
            }
        }
    }
    public static void putCookie (HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        Cookie usernameCookie = new Cookie("username", username);
        Cookie passwordCookie = new Cookie("password", password);
        usernameCookie.setMaxAge(60 * 3);
        if (remember != null) {
            passwordCookie.setMaxAge(60 * 3);

        } else {
            passwordCookie.setMaxAge(0);
        }
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
    }
}
