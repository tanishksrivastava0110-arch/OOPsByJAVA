
package com.kiet.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kiet.library.model.Book;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    // Get all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Get a book by ID
    public Optional<Book> getBookById(int bookId) {
        return books.stream()
                .filter(book -> book.getBookId() == bookId)
                .findFirst();
    }

    // Check whether a book ID already exists
    public boolean bookExists(int bookId) {
        return getBookById(bookId).isPresent();
    }

    // Add a new book
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    // Update an existing book
    public Optional<Book> updateBook(int bookId, Book updatedBook) {
        Optional<Book> existingBook = getBookById(bookId);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();

            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setCategory(updatedBook.getCategory());
            book.setPublicationYear(updatedBook.getPublicationYear());

            return Optional.of(book);
        }

        return Optional.empty();
    }

    // Delete a book
    public boolean deleteBook(int bookId) {
        return books.removeIf(book -> book.getBookId() == bookId);
    }

    // Search books by category
    public List<Book> searchByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory() != null
                        && book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Find books published from a specified year
    public List<Book> findPublishedFromYear(int fromYear) {
        return books.stream()
                .filter(book -> book.getPublicationYear() >= fromYear)
                .collect(Collectors.toList());
    }
}