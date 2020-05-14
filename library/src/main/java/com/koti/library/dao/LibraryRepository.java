package com.koti.library.dao;

import com.koti.library.dao.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Library, Integer> {
    Library findByLibraryName(String libraryName);
}
