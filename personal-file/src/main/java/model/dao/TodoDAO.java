package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;
import model.bean.TodoBean;

public class TodoDAO {

	//Todo一覧表示
	public List <TodoBean> listAllTodos() throws ClassNotFoundException, SQLException {
		List <TodoBean> todoList = new ArrayList<>();
		String sql = "SELECT * FROM todos";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet res = pstmt.executeQuery()) {
			while(res.next()) {
				TodoBean todo = new TodoBean();
				todo.setTodoId(res.getInt("todo_id"));
				todo.setTask(res.getString("task"));
				todo.setDueDate(res.getDate("due_date"));
				todo.setStatus(res.getBoolean("status"));
				todo.setPriority(res.getString("priority"));

				todoList.add(todo);
			}
			return todoList;
		}
	}

	//Todo新規登録
	public void createTodo(String task, java.sql.Date dueDate, String priority) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO todos (task, due_date, status, priority) VALUES (?, ?, ?, ?)";
		boolean status = false;
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, task);
			pstmt.setDate(2, dueDate);
			pstmt.setBoolean(3, status);
			pstmt.setString(4, priority);
			pstmt.executeUpdate();
		}
	}
	
	//Todoステータス更新
	public void updateTodoStatus(int todoId, boolean newStatus) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE todos SET status = ? WHERE todo_id = ?";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBoolean(1, newStatus);
			pstmt.setInt(2, todoId);
			pstmt.executeUpdate();
		}
	}
	
	//Todoステータスtrue(完了済み)のレコードを物理削除
	public void deleteCompletedTodos() throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM todos WHERE status = TRUE";
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.executeUpdate();
		}
	}
}
