package model.servlet.todo;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.TodoDAO;

@WebServlet("/deleteCompletedTodos")
public class DeleteCompletedTodosServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TodoDAO todoDao = new TodoDAO();
		
		try {
			todoDao.deleteCompletedTodos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("todoListServlet");
	}
}
