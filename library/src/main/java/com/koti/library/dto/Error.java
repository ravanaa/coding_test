package com.koti.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

  private Integer errorCode;
  private String errorDescription;
}
