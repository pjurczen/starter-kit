package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.BookTo;

public interface LibraryService {
    void removeLibraryById(Long id);
    List<BookTo> findAllBooksByLibraryId(Long id);
}
