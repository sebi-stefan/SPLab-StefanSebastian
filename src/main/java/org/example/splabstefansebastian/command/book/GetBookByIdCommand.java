package org.example.splabstefansebastian.command.book;

import org.example.splabstefansebastian.command.AbstractCommand;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.service.BookService;

public class GetBookByIdCommand extends AbstractCommand<Book> {

    private Integer bookId;

    public GetBookByIdCommand(BookService bookService, Integer bookId) {
        super(bookService);
        this.bookId = bookId;
    }

    @Override
    public Book execute() {
        return bookService.getBookById(bookId);
    }
}
