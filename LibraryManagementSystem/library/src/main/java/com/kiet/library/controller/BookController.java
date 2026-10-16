package com.kiet.library.controller;

import com.kiet.library.model.Book;
import com.kiet.library.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // Constructor injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET: Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET: Retrieve a book by ID
    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(
            @PathVariable("bookId") int bookId) {

        Optional<Book> book = bookService.getBookById(bookId);

        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Book not found"));
    }

    // POST: Add a new book
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        if (bookService.bookExists(book.getBookId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Book ID already exists"));
        }

        Book savedBook = bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBook);
    }

    // PUT: Update an existing book
    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable("bookId") int bookId,
            @RequestBody Book updatedBook) {

        Optional<Book> updated = bookService.updateBook(bookId, updatedBook);

        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Book not found"));
    }

    // DELETE: Delete a book
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Map<String, String>> deleteBook(
            @PathVariable("bookId") int bookId) {

        boolean deleted = bookService.deleteBook(bookId);

        if (deleted) {
            return ResponseEntity.ok(
                    Map.of("message", "Book deleted successfully"));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Book not found"));
    }

    // GET: Search books by category
    @GetMapping("/search")
    public ResponseEntity<?> searchByCategory(
            @RequestParam("category") String category) {

        List<Book> result = bookService.searchByCategory(category);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message",
                            "No books found in this category"));
        }

        return ResponseEntity.ok(result);
    }

    // GET: Filter books by publication year
    @GetMapping("/published")
    public ResponseEntity<?> findPublishedFromYear(
            @RequestParam("fromYear") int fromYear) {

        List<Book> result = bookService.findPublishedFromYear(fromYear);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "message",
                            "No books found for the specified year"));
        }

        return ResponseEntity.ok(result);
    }
}