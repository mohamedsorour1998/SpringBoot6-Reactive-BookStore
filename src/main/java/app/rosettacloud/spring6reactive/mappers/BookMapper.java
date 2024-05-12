package app.rosettacloud.spring6reactive.mappers;

import org.mapstruct.Mapper;

import app.rosettacloud.spring6reactive.domain.Book;
import app.rosettacloud.spring6reactive.model.BookDTO;

@Mapper
public interface BookMapper {

    BookDTO toDto(Book book);
    Book toBook(BookDTO bookDTO);

}
