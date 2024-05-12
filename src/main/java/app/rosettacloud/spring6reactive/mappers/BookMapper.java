package app.rosettacloud.spring6reactive.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import app.rosettacloud.spring6reactive.domain.Book;
import app.rosettacloud.spring6reactive.model.BookDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface BookMapper {

    BookDTO toDto(Book book);

    Book toBook(BookDTO bookDTO);

}
