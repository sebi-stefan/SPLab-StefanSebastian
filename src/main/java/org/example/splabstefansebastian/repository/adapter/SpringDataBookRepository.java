package org.example.splabstefansebastian.repository.adapter;

import org.example.splabstefansebastian.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataBookRepository extends JpaRepository<Book, Long> {
}
