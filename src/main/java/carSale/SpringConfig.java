package carSale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("carSale")
public class SpringConfig {
    private static Logger logger = LogManager.getLogger(SpringConfig.class);

    @Bean
    public Classic classicConfiguration() {
        logger.info("bean classic");
        return new Classic();
    }

    @Bean
    public Sport sportConfiguration() {
        logger.info("bean sport");
        return new Sport();
    }

    @Bean
    public Luxe luxeConfiguration() {
        logger.info("bean luxe");
        return new Luxe();
    }
    @Bean Car classicCar() {
        return new Car(classicConfiguration());
    }

    @Bean Car sportCar() {
        return new Car(sportConfiguration());
    }

    @Bean Car luxeCar() {
        return new Car(luxeConfiguration());
    }
}
