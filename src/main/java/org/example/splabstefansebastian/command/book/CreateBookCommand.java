package org.example.splabstefansebastian.command.book;

import org.example.splabstefansebastian.command.AbstractCommand;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.service.BookService;

public class CreateBookCommand extends AbstractCommand<Book> {

    private Book bookToCreate;

    public CreateBookCommand(BookService bookService, Book bookToCreate) {
        super(bookService);
        this.bookToCreate = bookToCreate;
    }

    @Override
    public Book execute() {
        return bookService.createBook(bookToCreate);
    }
}
