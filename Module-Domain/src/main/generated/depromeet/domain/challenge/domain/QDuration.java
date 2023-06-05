package depromeet.domain.challenge.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QDuration is a Querydsl query type for Duration */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDuration extends BeanPath<Duration> {

    private static final long serialVersionUID = -601920220L;

    public static final QDuration duration = new QDuration("duration");

    public final DatePath<java.time.LocalDate> endAt =
            createDate("endAt", java.time.LocalDate.class);

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final DatePath<java.time.LocalDate> startAt =
            createDate("startAt", java.time.LocalDate.class);

    public QDuration(String variable) {
        super(Duration.class, forVariable(variable));
    }

    public QDuration(Path<? extends Duration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDuration(PathMetadata metadata) {
        super(Duration.class, metadata);
    }
}
