package com.org.usercontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.dao.UserDao;
import com.org.dto.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		long mobile = Long.parseLong(req.getParameter("mobile"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user =  new User();
		user.setName(name);
		user.setAge(age);
		user.setEmail(email);
		user.setPassword(password);
		user.setMobile(mobile);
		
		UserDao dao = new UserDao();
		dao.saveUser(user);
		
		resp.sendRedirect("login.jsp");
	}

}
