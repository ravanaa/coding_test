package com.koti.library.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer isbn;

  @Column(name = "title",nullable = false)
  private String bookTitle;
  @Column(name="language")
  private String language;
  @Column(name="pub_year")
  private Integer publishYear;
  @Column(name="author")
  private String author;
//  @Column(name="library_id",nullable = false)
//  private Integer libraryID;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="library_id",nullable = false)
  private Library library;

}
