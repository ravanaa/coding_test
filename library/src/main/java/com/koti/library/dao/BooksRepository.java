package com.koti.library.dao;

import com.koti.library.dao.entity.Book;
import com.koti.library.dao.entity.Library;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends CrudRepository<Book, Integer> {
  List<Book> findByLibrary(Library library);
}
