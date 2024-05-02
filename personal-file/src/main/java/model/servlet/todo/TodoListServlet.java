package model.servlet.todo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.TodoBean;
import model.dao.TodoDAO;

@WebServlet("/todoListServlet")
public class TodoListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TodoDAO todoDao = new TodoDAO();
		List<TodoBean> todos;
		try {
			todos = todoDao.listAllTodos();
			request.setAttribute("todos", todos);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String view = "/WEB-INF/views/todo.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}