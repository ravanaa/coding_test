package com.koti.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.koti.library.dao.LibraryRepository;
import com.koti.library.dao.entity.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LibraryApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void getLibraries(){
		try {
			mockMvc.perform(get("/library/api/getAllLibraries")).andExpect(status().isOk());
		}catch (Exception ex){

		}
	}

	@Test
	void getBooks(){
		try{
			mockMvc.perform(get("library/api/getBooks/1")).andExpect(status().isOk());
		}catch(Exception ex){

		}
	}

	@Test
	void addLibrary(){
		Library library = new Library();
		library.setLibraryName("TestLibrary");
		library.setDescription("Test Library description");

		try{
			mockMvc.perform(post("/library/api/addLibrary")
					.contentType("application/json")
					.content(objectMapper.writeValueAsString(library))
			).andExpect(status().isOk());

			Library libEntity = libraryRepository.findByLibraryName("TestLibrary");
			System.out.println("Library Id: "+libEntity.getLibraryId());
			assertEquals("TestLibrary",libEntity.getLibraryName());
		}catch (Exception ex){

		}
	}

	void deleteLibrary(){}
	void addAndDeleteBook(){}
}
