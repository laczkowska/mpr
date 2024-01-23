package org.example.dto;

import org.example.data.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);
}
