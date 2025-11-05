package org.example.splabstefansebastian.command.book;

import org.example.splabstefansebastian.command.AbstractCommand;
import org.example.splabstefansebastian.service.BookService;

public class DeleteBookCommand extends AbstractCommand<Void> {

    private Long bookId;

    public DeleteBookCommand(BookService bookService, Long bookId) {
        super(bookService);
        this.bookId = bookId;
    }

    @Override
    public Void execute() {
        return bookService.deleteBook(bookId);
    }
}
