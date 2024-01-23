package org.example.dto;

import org.example.data.Book;
import org.example.dto.BookDTO;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);
}
