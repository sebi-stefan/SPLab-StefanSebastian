package org.example.splabstefansebastian.command.book;

import org.example.splabstefansebastian.command.AbstractCommand;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.service.BookService;

public class ModifyBookCommand extends AbstractCommand<Book> {

    private Book bookToModify;
    private Integer bookId;

    public ModifyBookCommand(BookService bookService, Book bookToModify, Integer bookId) {
        super(bookService);
        this.bookToModify = bookToModify;
        this.bookId = bookId;
    }

    @Override
    public Book execute() {
        return bookService.modifyBook(bookToModify, bookId);
    }
}
