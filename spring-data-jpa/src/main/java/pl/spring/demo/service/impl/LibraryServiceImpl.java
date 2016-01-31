package pl.spring.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.BookTo;

@Service
public class LibraryServiceImpl implements LibraryService {
    
    private LibraryRepository libraryRepository;
    
    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void removeLibraryById(Long id) {
        libraryRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookTo> findAllBooksByLibraryId(Long id) {
        return BookMapper.map2To(libraryRepository.getOne(id)
                .getBooks()
                .stream()
                .collect(Collectors.toList()));
    }
}
