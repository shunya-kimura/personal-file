package model.servlet.todo;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.TodoDAO;

@WebServlet("/updateTodoStatus")
public class UpdateTodoStatusServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int todoId = Integer.parseInt(request.getParameter("todoId"));
		boolean newwStatus = request.getParameter("status") != null;
		
		TodoDAO todoDao = new TodoDAO();
		try {
			todoDao.updateTodoStatus(todoId, newwStatus);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		response.sendRedirect("todoListServlet");
	}
}
