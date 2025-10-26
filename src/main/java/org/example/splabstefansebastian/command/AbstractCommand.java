package org.example.splabstefansebastian.command;

import org.example.splabstefansebastian.service.BookService;

public abstract class AbstractCommand<T> implements Command<T> {

    protected BookService bookService;

    public AbstractCommand(BookService bookService){
        this.bookService = bookService;
    }
}
