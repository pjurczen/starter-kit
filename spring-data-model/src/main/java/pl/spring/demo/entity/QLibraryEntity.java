package pl.spring.demo.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QLibraryEntity is a Querydsl query type for LibraryEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLibraryEntity extends EntityPathBase<LibraryEntity> {

    private static final long serialVersionUID = -297537073L;

    public static final QLibraryEntity libraryEntity = new QLibraryEntity("libraryEntity");

    public final SetPath<BookEntity, QBookEntity> books = this.<BookEntity, QBookEntity>createSet("books", BookEntity.class, QBookEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QLibraryEntity(String variable) {
        super(LibraryEntity.class, forVariable(variable));
    }

    public QLibraryEntity(Path<? extends LibraryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLibraryEntity(PathMetadata<?> metadata) {
        super(LibraryEntity.class, metadata);
    }

}

