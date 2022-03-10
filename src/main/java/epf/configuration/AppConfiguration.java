package epf.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"epf.service","epf.dao","epf.persistence"})//package dans lesquels chercher les beans

public class AppConfiguration {

}
