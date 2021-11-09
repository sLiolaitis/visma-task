package com.simas.vismajavadevelopertask.repository;

import com.simas.vismajavadevelopertask.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
