package com.koti.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

  private Integer ISBN;
  private String bookTitle;
  private String language;
  private Integer publishingYear;
  private String author;

}
