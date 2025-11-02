package org.example.splabstefansebastian.service;

import lombok.RequiredArgsConstructor;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService{

    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        try {
            Optional<Book> book = bookRepository.findById(id);
            if(book.isPresent()) return book.get();
            else throw new RuntimeException(String.format("Book with id: % does not exist", id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    public Book modifyBook(Book book, Long id){

        try {
            Book foundBook = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(String.format("Book with id: % does not exist", id)));

            mapToEntity(foundBook, book);
            return bookRepository.save(foundBook);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Void deleteBook(Long id){
        try{
            bookRepository.deleteById(id);
        }catch (Exception e){

        }
        return null;
    }

    private void mapToEntity(Book entitySource, Book entityDestination){
        entityDestination.setTitle(entitySource.getTitle());
        entityDestination.setAuthors(entitySource.getAuthors());
        entityDestination.setElements(entitySource.getElements());
    }

}
