package com.koti.library.service;

import com.koti.library.dao.BooksRepository;
import com.koti.library.dao.LibraryRepository;
import com.koti.library.dao.entity.Book;
import com.koti.library.dao.entity.Library;
import com.koti.library.dto.BookDTO;
import com.koti.library.dto.LibraryDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService implements ILibraryService {

  @Autowired
  private LibraryRepository libraryRepository;
  @Autowired
  private BooksRepository booksRepository;

  @Override
  public boolean addLibrary(LibraryDTO libraryDTO) {
    Library library = new Library();
    library.setLibraryName(libraryDTO.getLibraryName());
    library.setDescription(libraryDTO.getDescripiton());
    libraryRepository.save(library);
    return true;
  }

  @Override
  public LibraryDTO getLibrary(Integer libraryID) {
    Optional<Library> library = libraryRepository.findById(libraryID);
    if(library.isPresent()){
      LibraryDTO libraryDTO = new LibraryDTO();
      libraryDTO.setLibraryId(library.get().getLibraryId());
      libraryDTO.setLibraryName(library.get().getLibraryName());
      libraryDTO.setDescripiton(library.get().getDescription());
      return libraryDTO;
    }

    return null;
  }

  @Override
  public List<LibraryDTO> getAllLibraries() {
    List<Library> libraries = libraryRepository.findAll();
    List<LibraryDTO> libraryDTOList = libraries.stream().map(library -> {
      LibraryDTO dto = new LibraryDTO();
      dto.setLibraryId(library.getLibraryId());
      dto.setLibraryName(library.getLibraryName());
      dto.setDescripiton(library.getDescription());
      return dto;
          }).collect(
        Collectors.toList());
    return libraryDTOList;
  }

  @Override
  public boolean addBook(BookDTO bookDTO, Integer libraryID) {
    Book book = new Book();
    book.setIsbn(bookDTO.getISBN());
    book.setBookTitle(bookDTO.getBookTitle());
    book.setLanguage(bookDTO.getLanguage());
    book.setAuthor(bookDTO.getAuthor());
    book.setLibrary(new Library(libraryID));
    booksRepository.save(book);
    return true;
  }

  @Override
  public List<BookDTO> getBooks(Integer libraryID) {
    List<Book> bookList = booksRepository.findByLibrary(new Library(libraryID));
    List<BookDTO> bookDTOList = bookList.parallelStream().map(book -> {
      return new BookDTO(book.getIsbn(),book.getBookTitle(),book.getLanguage(),book.getPublishYear(),book.getAuthor());
    }).collect(Collectors.toList());
    return bookDTOList;
  }
}
