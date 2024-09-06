package com.ssg.w1.todo;

import com.ssg.w1.todo.dto.TodoDTO;
import com.ssg.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoRemoveController", urlPatterns = "/todo/remove")
public class TodoRemoveController extends HttpServlet {
//    // GET 요청을 처리하여 항목 삭제
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("REMOVE 버튼을 통해 Todo 항목을 삭제하였습니다~");
//
//        try {
//            Long id = Long.parseLong(req.getParameter("tno"));
//            TodoService.INSTANCE.remove(id); // 항목 삭제 처리
//            // 삭제 후 /todo/read로 리디렉션
//            resp.sendRedirect("/todo/read");
//        } catch (NumberFormatException e) {
//            // tno가 유효하지 않은 경우 예외 처리
//            System.out.println("유효하지 않은 tno 값: " + req.getParameter("tno"));
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 Todo 항목 ID");
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tno = req.getParameter("tno");
        TodoService.INSTANCE.deleteTodo(tno);
        resp.sendRedirect("/todo/list");
    }
}
