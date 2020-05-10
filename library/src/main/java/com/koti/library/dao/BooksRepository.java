package com.koti.library.dao;

import com.koti.library.dao.entity.Book;
import com.koti.library.dao.entity.Library;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
  List<Book> findByLibrary(Library library);
}
