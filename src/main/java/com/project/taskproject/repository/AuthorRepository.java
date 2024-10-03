package com.project.taskproject.repository;

import com.project.taskproject.domain.Author;
import com.project.taskproject.dto.response.AuthorResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createAuthor(Author author) {
        String sql = "INSERT INTO authors(author_name, email) VALUES (?, ?)";
        return jdbcTemplate.update(sql, author.getAuthor_name(), author.getEmail());
    }
    public List<AuthorResDto> findAll(){
        String sql = "SELECT * FROM authors";
        return jdbcTemplate.query(sql, new RowMapper<AuthorResDto>() {
            @Override
            public AuthorResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String author_name = rs.getString("author_name");
                String email = rs.getString("email");
                LocalDateTime joinTime = rs.getTimestamp("joinTime").toLocalDateTime();
                LocalDateTime editTIme = rs.getTimestamp("editTime").toLocalDateTime();
                return new AuthorResDto(id,author_name,email,joinTime,editTIme);
            }
        });
    }

    public Author findById(Long id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Author(
                        rs.getLong("id"),
                        rs.getString("author_name"),
                        rs.getString("email"),
                        rs.getTimestamp("createTime").toLocalDateTime(),
                        rs.getTimestamp("editTime").toLocalDateTime()
                )
        );
    }
    public int updateAuthor(Author author) {
        String sql = "UPDATE authors SET author_name = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, author.getAuthor_name(), author.getEmail(), author.getId());
    }

    public int deleteAuthor(Long id) {
        String sql = "DELETE FROM authors WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
