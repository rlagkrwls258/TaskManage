package com.project.taskproject.repository;
import com.project.taskproject.dto.response.TaskResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import com.project.taskproject.domain.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createTask(Task task) {
        String sql = "INSERT INTO tasks(title, author_id, author_name, password, createTime, editTime) VALUES (?, ?, ?, ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        return jdbcTemplate.update(sql, task.getTitle(), task.getAuthor_id(), task.getAuthor_name(), task.getPassword(), now, now);
    }

    public List<TaskResDto> findAll(){
        String sql = "SELECT * FROM tasks";

        return jdbcTemplate.query(sql, new RowMapper<TaskResDto>() {
            @Override
            public TaskResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                Long author_id = rs.getLong("author_id");
                String author_name = rs.getString("author_name");
                String title = rs.getString("title");
                LocalDateTime createTime = rs.getTimestamp("createTime").toLocalDateTime();
                LocalDateTime editTIme = rs.getTimestamp("editTime").toLocalDateTime();
                return new TaskResDto(id,author_id,author_name,title,createTime,editTIme);
            }
        });
    }

    public Task findById(Long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Task(
                        rs.getLong("id"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getString("title"),
                        rs.getString("password"),
                        rs.getTimestamp("createTime").toLocalDateTime(),
                        rs.getTimestamp("editTime").toLocalDateTime()
                )
        );
    }

    public List<TaskResDto> findAllByFilters(String authorName, String editDate) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tasks WHERE 1=1");

        // 동적 쿼리: 작성자명이 있는 경우
        if (authorName != null && !authorName.isEmpty()) {
            sql.append(" AND author_name = ?");
        }

        // 동적 쿼리: 수정일이 있는 경우
        if (editDate != null && !editDate.isEmpty()) {
            sql.append(" AND DATE(editTime) = ?");
        }

        // 매개변수를 저장할 리스트 생성
        List<Object> params = new ArrayList<>();
        if (authorName != null && !authorName.isEmpty()) {
            params.add(authorName);
        }
        if (editDate != null && !editDate.isEmpty()) {
            params.add(editDate);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) ->
                new TaskResDto(
                        rs.getLong("id"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getString("title"),
                        rs.getTimestamp("createTime").toLocalDateTime(),
                        rs.getTimestamp("editTime").toLocalDateTime()
                )
        );
    }

    public int updateTask(Task task) {
        String sql = "UPDATE tasks SET title = ?, author_name = ? WHERE id = ?";
        return jdbcTemplate.update(sql, task.getTitle(), task.getAuthor_name(), task.getId());
    }

    public int deleteTask(Long id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<TaskResDto> findAllWithPaging(int page, int size) {
        String sql = "SELECT * FROM tasks ORDER BY createTime DESC LIMIT ? OFFSET ?";
        int offset = (page - 1) * size;

        return jdbcTemplate.query(sql, new Object[]{size, offset}, (rs, rowNum) ->
                new TaskResDto(
                        rs.getLong("id"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getString("title"),
                        rs.getTimestamp("createTime").toLocalDateTime(),
                        rs.getTimestamp("editTime").toLocalDateTime()
                )
        );
    }
    public int countTasks() {
        String sql = "SELECT COUNT(*) FROM tasks";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
