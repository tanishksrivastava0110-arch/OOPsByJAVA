package com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Book;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    // In-memory storage
    private List<Book> books = new ArrayList<>();

    // (b) Add a new book using POST

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {

        // Check duplicate ID
        for (Book b : books) {
            if (b.getId().equals(book.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Book ID already exists!");
            }
        }

        books.add(book);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book Added Successfully!");
    }

    // (c) Retrieve all books using GET

    @GetMapping
    public List<Book> getAllBooks() {

        return books;
    }

    // Retrieve a specific book by ID

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {

        for (Book b : books) {

            if (b.getId().equals(id)) {
                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book Not Found!");
    }

    // (d) Update book details using PUT

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        for (Book b : books) {

            if (b.getId().equals(id)) {

                b.setTitle(updatedBook.getTitle());
                b.setAuthor(updatedBook.getAuthor());
                b.setCategory(updatedBook.getCategory());
                b.setAvailableCopies(
                        updatedBook.getAvailableCopies());

                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book Not Found!");
    }

    // (e) Delete book using DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable Integer id) {

        for (Book b : books) {

            if (b.getId().equals(id)) {

                books.remove(b);

                return ResponseEntity.ok(
                        "Book Deleted Successfully!");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book Not Found! Cannot Delete.");
    }
}
