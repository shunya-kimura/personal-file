<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.TodoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
  .completed {
    text-decoration: line-through;
  }
</style>
<body>
  <div class="container mt-5">
    <!-- 新規Todo作成ボタン -->
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createTodoModal">Create new Todo</button>
  
  <form action="deleteCompletedTodos" method="get">
    <button type="submit" class="btn btn-danger">完了したTodoを削除</button>
  </form>

  <h2>Todo List</h2>
  <table border="1">
    <tr>
      <th></th> <!-- チェックボックス用の列 -->
      <th>タスク</th>
      <th>期日</th>
      <th>優先度</th>
    </tr>
    <%
    List<TodoBean> todos = (List<TodoBean>) request.getAttribute("todos");
    if (todos != null && !todos.isEmpty()) {
    	for (TodoBean todo : todos) {
    %>
      <form action="updateTodoStatus" method="post">
      <tr class="<%= todo.isStatus() ? "completed" : "" %>">
        <td>
          <input type="checkbox" name="status" onchange="this.form.submit();" <%= todo.isStatus() ? "checked" : "" %>>
          <input type="hidden" name="todoId" value="<%= todo.getTodoId() %>">
        </td>
        <td><%= todo.getTask() %></td>
        <td><%= todo.getDueDate() != null ? todo.getDueDate().toString() : "N/A" %></td>
        <td><%= todo.getPriority() %></td>
      </tr>
      </form>
    <%
      }
    }
    %>
  </table>
  
  
   <!-- 新規Todo作成モーダル -->
    <div class="modal fade" id="createTodoModal" tabindex="-1" aria-labelledby="createTodoModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="createTodoModal">Create New Todo</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <form action="todoCreateServlet" method="post">
              <div class="form-group">
                <label for="task">タスク</label>
                <input type="text" class="form-control" id="task" name="task" required>
              </div>
              <div class="form-group">
                <label for="dueDate">期日</label>
                <input type="date" class="form-control" id="dueDate" name="dueDate" required>
              </div>
              <div class="form-group">
                <label for="priority">優先度</label>
                <select class="form-control" id="priority" name="priority">
                  <option>High</option>
                  <option>Medium</option>
                  <option>Low</option>
                </select>
              </div>
              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  
   <!-- Bootstrap and jQuery -->
   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>