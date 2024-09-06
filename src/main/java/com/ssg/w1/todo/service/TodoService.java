package com.ssg.w1.todo.service;

import com.ssg.w1.todo.dao.TodoDAO;
import com.ssg.w1.todo.domain.TodoVO;
import com.ssg.w1.todo.dto.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

public enum TodoService {
    INSTANCE;
    private final TodoDAO todoDAO = new TodoDAO();

    //글 하나를 등록하는 기능
    public void register(TodoDTO dto){
        System.out.println("register the todo list");
        todoDAO.insert(dto);
    }


    //등록된 글 목록 반환하는 기능   10개의 TodoDTO (글) 을 만들어서 리스트 객체로 반환
    public List<TodoDTO> getList(){
        List<TodoVO> todoVOS = todoDAO.selectAllList();
        List<TodoDTO> todoDTOS = todoVOS.stream().map(i->{

            TodoDTO dto = new TodoDTO();
            dto.setTno(i.getTno());
            dto.setTitle(i.getTitle());
            dto.setDueDate(i.getDueDate());
            dto.setFinished(i.isFinished());
            return dto;

        }).collect(Collectors.toList());
        return todoDTOS;
    }

    public TodoDTO get(Long tno){
        TodoVO vo = todoDAO.selectOne(tno);
        TodoDTO dto = new TodoDTO();
        dto.setTno(vo.getTno());
        dto.setTitle(vo.getTitle());
        dto.setDueDate(vo.getDueDate());
        dto.setFinished(vo.isFinished());
        return dto;
    }

//    public void remove(Long tno) {
//        try {
//            todoDAO.deleteOne(tno); // TodoDAO의 deleteOne 메서드를 호출하여 삭제
//        } catch (Exception e) {
//            throw new RuntimeException("Todo 삭제 중 오류 발생 (tno: " + tno + ")", e);
//        }
//    }

    public void deleteTodo(String tno){
        todoDAO.delete(tno);
    }

    public void modify(TodoDTO todoDTO){
        todoDAO.updateOne(todoDTO);
    }
}
