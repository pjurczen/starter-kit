package pl.spring.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {
    @Autowired
    private LibraryRepository libraryRepository;
    
    @Test
    public void testShouldFindLibrariesByName() {
        // given
        final String libraryName = "p";
        LibraryEntity testLibraryEntity = new LibraryEntity(null, "Publiczna Biblioteka Miejska Nr 1");
        libraryRepository.save(testLibraryEntity);
        // when
        List<LibraryEntity> foundLibraries = libraryRepository.findLibraryByName(libraryName);
        // then
        assertNotNull(foundLibraries);
        assertFalse(foundLibraries.isEmpty());
        assertTrue(foundLibraries.contains(testLibraryEntity));
    }
}
