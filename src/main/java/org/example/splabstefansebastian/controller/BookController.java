package org.example.splabstefansebastian.controller;

import lombok.RequiredArgsConstructor;
import org.example.splabstefansebastian.command.AsynchronousCommandExecutor;
import org.example.splabstefansebastian.command.Command;
import org.example.splabstefansebastian.command.CommandExecutor;
import org.example.splabstefansebastian.command.SynchronousCommandExecutor;
import org.example.splabstefansebastian.command.book.*;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final CommandExecutor syncExecutor;
    private final CommandExecutor asyncExecutor;

    public BookController(BookService bookService,
                          SynchronousCommandExecutor syncExecutor,
                          AsynchronousCommandExecutor asyncExecutor) {
        this.bookService = bookService;
        this.syncExecutor = syncExecutor;
        this.asyncExecutor = asyncExecutor;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        Command<List<Book>> getAllBooksCommand = new GetAllBooksCommand(bookService);

        return ResponseEntity.ok(syncExecutor.executeCommand(getAllBooksCommand));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){
        Command<Book> getBookByIdCommand = new GetBookByIdCommand(bookService, bookId);

        return ResponseEntity.ok(syncExecutor.executeCommand(getBookByIdCommand));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book){
        Command<Book> createBookCommand = new CreateBookCommand(bookService, book);

        asyncExecutor.executeCommand(createBookCommand);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> modifyBook(@RequestBody Book book, @PathVariable Long bookId){
        Command<Book> modifyBookCommand = new ModifyBookCommand(bookService, book, bookId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(syncExecutor.executeCommand(modifyBookCommand));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){
        Command<Void> deleteBookCommand = new DeleteBookCommand(bookService, bookId);
        syncExecutor.executeCommand(deleteBookCommand);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping("/books-sse")
    public ResponseBodyEmitter getBooksSse(){
        final SseEmitter emitter = new SseEmitter(0L);
        allBooksSubject.attach(new SseObserver(emitter));
        return emitter;
    }

}
