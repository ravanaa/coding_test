package com.koti.library.controller;

import com.koti.library.dto.BookDTO;
import com.koti.library.dto.Error;
import com.koti.library.dto.LibraryDTO;
import com.koti.library.dto.Response;
import com.koti.library.service.ILibraryService;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/api")
public class LibraryController {

  @Autowired
  ILibraryService libraryService;

  @GetMapping("/getLibraries")
  public String GetLibrary(){
    return "library";
  }

  @PostMapping("/addLibrary")
  public ResponseEntity<Response> addLibrary(@RequestBody LibraryDTO library){
//    System.out.println("library:"+library);
      Response response = new Response();
      response.setStatus(libraryService.addLibrary(library));
      response.setDate(LocalDateTime.now());
      return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/getAllLibraries")
  public ResponseEntity getAllLibraries(){
    Response response = new Response();
    response.setDate(LocalDateTime.now());
    List<LibraryDTO> libraryDTOList = libraryService.getAllLibraries();
    if(libraryDTOList!=null && libraryDTOList.size()>0){
      response.setData(libraryDTOList);
      response.setStatus(true
      );
    }else {
      response.setStatus(false);
      response.setError(new Error(404,"No libraries found"));
    }
    return new ResponseEntity(response,HttpStatus.OK);
  }

  @PostMapping("/addBook/{libraryId}")
  public ResponseEntity addBook(@PathVariable(name = "libraryId")Integer libraryId,@RequestBody
      BookDTO book){
    Response response = new Response();
    response.setDate(LocalDateTime.now());
    response.setStatus(libraryService.addBook(book,libraryId));
    if(!response.getStatus()) response.setError(new Error(503,"Failed add book to library"));
    return new ResponseEntity(response,HttpStatus.OK);
  }

  @GetMapping("/getBooks/{libraryId}")
  public ResponseEntity getBooks(@PathVariable(name = "libraryId")Integer libraryId){
    Response response = new Response();
    response.setDate(LocalDateTime.now());
    response.setData(libraryService.getBooks(libraryId));
    if(response.getData()!=null && response.getData().size()>0){
      response.setStatus(true);
    }else{
      response.setStatus(false);
      response.setError(new Error(404,"No books found "));
    }
    return new ResponseEntity(response,HttpStatus.OK);
  }
}
