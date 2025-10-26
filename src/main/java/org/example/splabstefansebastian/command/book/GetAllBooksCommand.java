package org.example.splabstefansebastian.command.book;

import org.example.splabstefansebastian.command.AbstractCommand;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.service.BookService;

import java.util.List;

public class GetAllBooksCommand extends AbstractCommand<List<Book>> {

    public GetAllBooksCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public List<Book> execute() {
        return bookService.getAllBooks();
    }
}
