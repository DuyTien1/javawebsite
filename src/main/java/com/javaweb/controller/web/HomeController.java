package com.javaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.UserModel;
import com.javaweb.service.IUserService;
import com.javaweb.utils.CookieUtil;
import com.javaweb.utils.FormUtil;
import com.javaweb.utils.SessionUtil;

//import com.javaweb.model.NewModel;
//import com.javaweb.service.INewService;

@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {
	
//	@Inject
//	private ICategoryService categoryService;
	
	@Inject
	private IUserService userService;

	private static final long serialVersionUID = 1L;

	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			CookieUtil.getCookie(request);
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERNMODEL");
			response.sendRedirect("/trang-chu");
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUsernameAndPasswordAndStatus(model.getUsername(), model.getPassword(), 1);
			if (model != null ) {
				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
				if (model.getRole().getCode().equals("USER")) {
					CookieUtil.putCookie(request, response);
					response.sendRedirect("/trang-chu");
				} else if (model.getRole().getCode().equals("ADMIN")) {
					CookieUtil.putCookie(request, response);
					response.sendRedirect("/admin-home");
				}
			}else {
				response.sendRedirect("/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		} 
	}
}
