package depromeet.api;


import depromeet.common.ModuleCommonApplication;
import depromeet.domain.ModuleDomainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(
        scanBasePackageClasses = {
            ModuleApiApplication.class,
            ModuleCommonApplication.class,
            ModuleDomainApplication.class
        })
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }
}
