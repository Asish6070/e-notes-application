package com.org.usercontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.org.dao.UserDao;
import com.org.dto.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDao dao = new UserDao();
		User user = dao.loginUser(email, password);

		HttpSession session = req.getSession();
		if (user != null) {
			session.setAttribute("userObj", user);
			resp.sendRedirect("home.jsp");
		} else {
			session.setAttribute("failed", "Invalid Credential");
			resp.sendRedirect("login.jsp");
		}
	}
}
