package carSale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CarSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSaleApplication.class, args);

////Сильные зависимости
//		Car carForFamily = new Car();
//		System.out.println(carForFamily.readyForSale());
//
//		Car carForYoung = new Car();
//		System.out.println(carForYoung.readyForSale());
//
//		Car carForBusines = new Car();
//		System.out.println(carForBusines.readyForSale());


 //Через конфигурации
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Car carForFamilyNew = context.getBean("classicCar", Car.class);
        Car carForYoungNew = context.getBean("sportCar", Car.class);
        Car carForBisunesNew = context.getBean("luxeCar", Car.class);
        System.out.println(carForFamilyNew.readyForSale());
        System.out.println(carForYoungNew.readyForSale());
        System.out.println(carForBisunesNew.readyForSale());
   }
}