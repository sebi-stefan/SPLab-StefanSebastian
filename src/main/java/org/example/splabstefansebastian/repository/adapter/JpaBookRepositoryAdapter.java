package org.example.splabstefansebastian.repository.adapter;

import lombok.RequiredArgsConstructor;
import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaBookRepositoryAdapter implements BookRepository {

    private final SpringDataBookRepository springDataRepository;

    @Override
    public Book save(Book entity) {
        return springDataRepository.save(entity);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return springDataRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return springDataRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        springDataRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springDataRepository.existsById(id);
    }
}
