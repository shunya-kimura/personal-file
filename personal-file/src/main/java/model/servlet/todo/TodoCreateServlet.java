package model.servlet.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.TodoDAO;

@WebServlet("/todoCreateServlet")
public class TodoCreateServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String task = request.getParameter("task");
		String dueDateString = request.getParameter("dueDate");
		String priority = request.getParameter("priority");
		
		try {
			java.sql.Date dueDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString).getTime());
            TodoDAO todoDao = new TodoDAO();
			todoDao.createTodo(task, dueDate, priority);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		response.sendRedirect("todoListServlet");
	}

}
