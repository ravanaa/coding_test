package com.koti.library.service;

import com.koti.library.dto.BookDTO;
import com.koti.library.dto.LibraryDTO;
import java.util.List;

public interface ILibraryService {
   boolean addLibrary(LibraryDTO libraryDTO);
   LibraryDTO getLibrary(Integer libraryID);
   List<LibraryDTO> getAllLibraries();
   boolean addBook(BookDTO bookDTO,Integer libraryID);
   List<BookDTO> getBooks(Integer libraryID);
}
