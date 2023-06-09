package depromeet.domain.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
import depromeet.domain.ModuleDomainApplication;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource("classpath:application-domain.yml")
@EntityScan(basePackageClasses = {ModuleDomainApplication.class})
@EnableJpaRepositories(basePackageClasses = {ModuleDomainApplication.class})
@EnableJpaAuditing
@EnableScheduling
@Configuration
public class DomainConfig {

    @PersistenceContext private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
