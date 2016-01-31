package pl.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.spring.demo.entity.LibraryEntity;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Long> {
    
    @Query("select library from LibraryEntity library where lower(library.name) like concat(lower(:name),'%')")
    List<LibraryEntity> findLibraryByName(@Param("name") String libraryName);
}
