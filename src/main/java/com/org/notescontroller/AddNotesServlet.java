package com.org.notescontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.org.dao.UserDao;
import com.org.dto.Notes;
import com.org.dto.User;

@WebServlet("/addNotes")
public class AddNotesServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		fetch details from form
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		
//		create notes and add details
		Notes notes = new Notes();
		notes.setTitle(title);
		notes.setDescription(description);
		
//		get user object from session
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("userObj");
		
//		add notes to list
		List<Notes> list = new ArrayList<Notes>();
		list.add(notes);
		
//		set User has many notes
		user.setNotes(list);
		
//		set Notes has user
		notes.setUser(user);
		
		UserDao dao = new UserDao();
//		If we save user automatically notes will be stored because of cascade
		dao.saveUser(user);
		
		session.setAttribute("success", "Notes Added Successfully");
		
		resp.sendRedirect("home.jsp");
	}
}
