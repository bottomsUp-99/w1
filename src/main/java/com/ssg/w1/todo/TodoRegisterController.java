package com.ssg.w1.todo;

import com.ssg.w1.todo.dto.TodoDTO;
import com.ssg.w1.todo.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name="todoRegisterController" , urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Todo 항목을 기입할 수 있는 창으로 왔습니다~");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/todo/register.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("REGISTER 버튼을 통해 Todo 항목을 넣었습니다~");
        String title = req.getParameter("title");
        String dueDate = req.getParameter("dueDate");

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle(title);
        todoDTO.setDueDate(LocalDate.parse(dueDate));
        TodoService.INSTANCE.register(todoDTO);
        resp.sendRedirect("/todo/list");
    }
}
