package pl.spring.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.to.AuthorTo;

public class AuthorMapper {

    public static AuthorTo map(AuthorEntity authorEntity) {
        if (authorEntity != null) {
            return new AuthorTo(authorEntity.getId(), authorEntity.getFirstName(), authorEntity.getLastName());
        }
        return null;
    }
    
    public static List<AuthorTo> map2To(List<AuthorEntity> authorEntities) {
        return authorEntities.stream().map(AuthorMapper::map).collect(Collectors.toList());
    }

}
