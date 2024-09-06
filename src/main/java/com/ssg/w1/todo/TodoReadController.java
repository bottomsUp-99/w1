package com.ssg.w1.todo;

import com.ssg.w1.todo.domain.TodoVO;
import com.ssg.w1.todo.dto.TodoDTO;
import com.ssg.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Todo 항목의 상세 내용을 보여줍니다~");

        ///todo/read?tno=123
        Long tno = Long.parseLong(req.getParameter("tno"));

        TodoDTO todoDTO = TodoService.INSTANCE.get(tno);

        req.setAttribute("dto",todoDTO);
        req.getRequestDispatcher("/todo/read.jsp").forward(req,resp);

    }
}
