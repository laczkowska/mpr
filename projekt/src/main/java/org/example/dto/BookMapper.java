package org.example.dto;

import org.example.data.Book;
import org.example.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);

    // Tutaj można dodać dodatkowe metody mapowania, jeśli są potrzebne
}
