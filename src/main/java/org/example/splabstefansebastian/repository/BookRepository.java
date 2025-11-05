package org.example.splabstefansebastian.repository;

import org.example.splabstefansebastian.model.Book;
import org.example.splabstefansebastian.repository.adapter.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
