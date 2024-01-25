package org.example.controller;

import org.example.dto.BookDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.example.controller.BookController;
import org.example.service.BookService;
import org.example.exception.ResourceNotFoundException;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void getBookById_HappyPath() throws Exception {
        BookDTO mockBook = new BookDTO(1L, "Test Title", "Test Author", "Test Category", 10.0, 5);
        Mockito.when(bookService.getBookById(1L)).thenReturn(mockBook);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Matchers.is("Test Author")));
        // Add more assertions as necessary
    }

    @Test
    public void getBookById_NotFound() throws Exception {
        Mockito.when(bookService.getBookById(1L)).thenThrow(new ResourceNotFoundException("Not Found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

