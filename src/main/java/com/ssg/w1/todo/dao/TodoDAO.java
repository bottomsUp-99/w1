package com.ssg.w1.todo.dao;

import com.ssg.w1.todo.domain.TodoVO;
import com.ssg.w1.todo.dto.TodoDTO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public void insert(TodoDTO vo) {
        try {
            String sql = "insert into tbl_todo (tno,title,dueDate,finished) values(null,?,?,null)";

            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setString(1, vo.getTitle());
            psmt.setDate(2, Date.valueOf(vo.getDueDate()));
            psmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TodoVO> selectAllList(){

        try {
            String sql = "select * from tbl_todo";

            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

            @Cleanup ResultSet rs = psmt.executeQuery();

            List<TodoVO> list = new ArrayList<>();

            while (rs.next()) {
                TodoVO vo = TodoVO.builder()
                        .tno(rs.getLong("tno"))
                        .title(rs.getString("title"))
                        .dueDate(rs.getDate("dueDate").toLocalDate())
                        .finished(rs.getBoolean("finished")).build();
                list.add(vo);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public TodoVO selectOne(long tno){


        try {
            String sql = "select * from tbl_todo where tno = ?";

            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setLong(1,tno);

            @Cleanup ResultSet rs = psmt.executeQuery();

            rs.next();

            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished")).build();

            return vo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //삭제기능은 조회 와 비슷하지만 쿼리(select)가 아니다.  삭제 특정번호가 필요하다.

//    public void deleteOne(Long tno){
//
//        String sql = "DELETE FROM tbl_todo WHERE tno = ?";
//
//        try {
//            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//            @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
//
//            psmt.setLong(1, tno);
//
//            int rowsAffected = psmt.executeUpdate(); // 쿼리 실행 후 영향을 받은 행 수 확인
//            if (rowsAffected == 0) {
//                throw new RuntimeException("Todo 항목을 찾을 수 없습니다. (tno: " + tno + ")");
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException("Todo 항목 삭제 중 오류 발생 (tno: " + tno + ")", e);
//        }
//    }

    public void delete(String tno) {
        String sql = "delete from tbl_todo where tno = ?";

        try {
            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, Long.parseLong(tno));
            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateOne(TodoDTO dto){

        try {
            String sql = "update tbl_todo set title = ? , dueDate = ? , finished =? where tno = ?";

            @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

            psmt.setString(1,dto.getTitle());
            psmt.setDate(2,Date.valueOf(dto.getDueDate()));
            psmt.setBoolean(3,dto.isFinished());
            psmt.setLong(4, dto.getTno());

            psmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
