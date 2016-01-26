package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class LibraryDaoImplTest {
    @Autowired
    private LibraryDao libraryDao;
    
    @Test
    public void testShouldSaveLibrary() {
        //given
        LibraryEntity testLibraryEntity = new LibraryEntity(null, "Publiczna Biblioteka Miejska Nr 1");
        Long librariesCountBeforeSave = libraryDao.count();
        //when
        libraryDao.save(testLibraryEntity);
        Long librariesCountAfterSave = libraryDao.count();
        //then
        assertTrue(librariesCountAfterSave == librariesCountBeforeSave + 1);
        assertTrue(libraryDao.findOne(librariesCountBeforeSave + 1).equals(testLibraryEntity));
    }
    
    @Test
    public void testShouldFindLibrariesByName() {
        // given
        final String libraryName = "p";
        LibraryEntity testLibraryEntity = new LibraryEntity(null, "Publiczna Biblioteka Miejska Nr 1");
        libraryDao.save(testLibraryEntity);
        // when
        List<LibraryEntity> foundLibraries = libraryDao.findLibraryByName(libraryName);
        // then
        assertNotNull(foundLibraries);
        assertFalse(foundLibraries.isEmpty());
        assertTrue(foundLibraries.contains(testLibraryEntity));
    }
}
