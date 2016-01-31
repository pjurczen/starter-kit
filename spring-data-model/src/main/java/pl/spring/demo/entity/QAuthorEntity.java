package pl.spring.demo.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAuthorEntity is a Querydsl query type for AuthorEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAuthorEntity extends EntityPathBase<AuthorEntity> {

    private static final long serialVersionUID = 1608137181L;

    public static final QAuthorEntity authorEntity = new QAuthorEntity("authorEntity");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public QAuthorEntity(String variable) {
        super(AuthorEntity.class, forVariable(variable));
    }

    public QAuthorEntity(Path<? extends AuthorEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorEntity(PathMetadata<?> metadata) {
        super(AuthorEntity.class, metadata);
    }

}

