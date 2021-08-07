package carSale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//public class Sport {
//    private static Logger logger = LogManager.getLogger(Sport.class);

//    //Сильные зависимости
//    public String makeConfiguration() {
//        logger.info("Сильные зависимости конфигурация sport");
//        return "Sport";
//    }


//Через интерфейс
public class Sport implements Confugurable {
    private static Logger logger = LogManager.getLogger(Sport.class);

    @Override
    public String makeConfiguration() {
        logger.info("Конфигурация sport через интерфейс");
        return "Sport";
    }


}
