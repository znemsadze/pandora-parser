package ge.pandora.init;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by vano on 2/19/16.
 */
@Configuration
@ComponentScan(basePackages = {"ge"})
public class RootConfig {


}

