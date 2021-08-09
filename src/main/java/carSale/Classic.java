package carSale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//public class Classic {
//    private static Logger logger = LogManager.getLogger(Classic.class);
//Сильные зависисмости
//    public String makeConfiguration() {
//        logger.info("Сильные зависимости конфигурация classic");
//        return "Classic";
//    }


    //Через интерфейс
    public class Classic implements Confugurable {
    private static Logger logger = LogManager.getLogger(Classic.class);
        @Override
        public String makeConfiguration() {
            logger.info("Конфигурация classic через интерфейс");
            return "Classic";

        }
    }
