package depromeet.api;


import depromeet.common.ModuleCommonApplication;
import depromeet.domain.ModuleDomainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackageClasses = {
            ModuleApiApplication.class,
            ModuleCommonApplication.class,
            ModuleDomainApplication.class
        })
@EntityScan(basePackageClasses = {ModuleDomainApplication.class})
@SpringBootApplication
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }
}
