package com.koti.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {

  private Integer libraryId;
  private String libraryName;
  private String descripiton;
}
