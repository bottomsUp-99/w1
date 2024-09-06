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
import java.util.Optional;

@WebServlet(name = "todoModifyController", urlPatterns = "/todo/modify")
public class TodoModifyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO todoDTO = new TodoDTO();
        String tno = req.getParameter("tno");
        String title = req.getParameter("title");
        String dueDate = req.getParameter("dueDate");
//        String finished = req.getParameter("finished");
//
//        if (!finished.isEmpty()){
//            finished = "true";
//        } else {
//            finished = "false";
//        }

        String finished = Optional.ofNullable(req.getParameter("finished"))
                .map(val -> "true")
                .orElse("false");

        todoDTO.setTno(Long.valueOf(tno));
        todoDTO.setTitle(title);
        todoDTO.setDueDate(LocalDate.parse(dueDate));
        todoDTO.setFinished(Boolean.parseBoolean(finished));
        TodoService.INSTANCE.modify(todoDTO);
        resp.sendRedirect("/todo/list");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tno = Long.parseLong(req.getParameter("tno"));

        TodoDTO todoDTO = TodoService.INSTANCE.get(tno);

        req.setAttribute("dto",todoDTO);
        req.getRequestDispatcher("/todo/modify.jsp").forward(req,resp);
    }
}
