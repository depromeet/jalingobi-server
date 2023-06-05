package depromeet.domain.config;


import depromeet.domain.ModuleDomainApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@PropertySource("classpath:application-domain.yml")
@EntityScan(basePackageClasses = {ModuleDomainApplication.class})
@EnableJpaRepositories(basePackageClasses = {ModuleDomainApplication.class})
@EnableJpaAuditing
@Configuration
public class JpaConfig {}
