package carSale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//
//public class Luxe {
//
//    private static Logger logger = LogManager.getLogger(Luxe.class);
//
//    //Cильные зависисмости
//    public String makeConfiguration() {
//        logger.info("Сильные зависимости конфигурация Luxe");
//        return "Luxe";
//    }
//

//Через интерфейс
public class Luxe implements Confugurable {
    private static Logger logger = LogManager.getLogger(Luxe.class);

    @Override
    public String makeConfiguration() {
        logger.info("Конфигурация Luxe через интерфейс");
        return "Luxe";

    }
}





