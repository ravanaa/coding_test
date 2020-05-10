package com.koti.library.dao.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "library")
@Data
@NoArgsConstructor
public class Library {

  public Library(Integer libraryId){
    this.setLibraryId(libraryId);
  }
  @Id
  @Column(name = "library_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer libraryId;

  @Column(name="library_name",nullable = false)
  private String libraryName;

  @Column(name="description")
  private String description;

  @OneToMany(cascade = CascadeType.ALL,mappedBy = "library",orphanRemoval = true,fetch = FetchType.LAZY)
  private Set<Book> books ;
}
