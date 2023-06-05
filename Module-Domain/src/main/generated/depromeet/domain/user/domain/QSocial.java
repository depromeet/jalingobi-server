package depromeet.domain.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QSocial is a Querydsl query type for Social */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSocial extends BeanPath<Social> {

    private static final long serialVersionUID = -1121735901L;

    public static final QSocial social = new QSocial("social");

    public final StringPath id = createString("id");

    public final EnumPath<Platform> platform = createEnum("platform", Platform.class);

    public QSocial(String variable) {
        super(Social.class, forVariable(variable));
    }

    public QSocial(Path<? extends Social> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSocial(PathMetadata metadata) {
        super(Social.class, metadata);
    }
}
