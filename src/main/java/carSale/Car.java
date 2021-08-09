package carSale;

public class Car {
    //Сильные зависимости
//    private Classic classicConfiguration;
//    private Luxe luxeConfiguration;
    // private Sport sportConfiguration;

//    public String readyForSale() {
//        classicConfiguration = new Classic();
//        return "Car Ready in: " + classicConfiguration.makeConfiguration();
//        luxeConfiguration = new Luxe();
//        return "Car Ready in: " + luxeConfiguration.makeConfiguration();
//        sportConfiguration = new Sport();
//        return "Car Ready in: " + sportConfiguration.makeConfiguration();

//    }


//    //Через интерфейс
//    private Confugurable configuration;
//
//    public String readyForSale() {
////        configuration = new Classic();
////        configuration = new Sport();
//        configuration = new Luxe();
//        return "Car Ready in: " + configuration.makeConfiguration();
//    }


    // Через конфигурации
    private Confugurable configuration;

    public Car(Confugurable configuration) {
        this.configuration = configuration;
    }

    public String readyForSale(){
        return "Car Ready in: " + configuration.makeConfiguration();
    }

}
