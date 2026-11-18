
package com.kiet.library.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kiet.library.model.Book;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbc;

    public BookRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Map database rows to Book objects
    private final RowMapper<Book> bookMapper = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            Book book = new Book();

            book.setBookId(rs.getInt("book_id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setPublicationYear(rs.getInt("publication_year"));
            book.setAvailable(rs.getBoolean("available"));

            return book;
        }
    };

    // Get all books
    public List<Book> findAll() {
        String sql = "SELECT * FROM books ORDER BY book_id";
        return jdbc.query(sql, bookMapper);
    }

    // Get a book by ID
    public Book findById(int bookId) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        List<Book> result = jdbc.query(sql, bookMapper, bookId);

        return result.isEmpty() ? null : result.get(0);
    }
}
