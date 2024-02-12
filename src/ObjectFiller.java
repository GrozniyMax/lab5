import Entities.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Класс для заполнения объекта типа Flat
 */
public class ObjectFiller {
    private static final Scanner sc = new Scanner(System.in);
    Flat rawFlat;
    /**
     * Конструктор
     */
    public ObjectFiller() {
        this.rawFlat = new Flat();
    }
    /**
     * Заполняет поле name объекта типа Flat
     */
    private void parceName() {
        System.out.print("Введите Имя:");
        this.rawFlat.setName(sc.nextLine());
    }
    /**
     * Заполняет поле coordinates объекта типа Flat
     */
    private void parceCoordinates() throws IllegalArgumentException{

        System.out.print("Введите Координаты в формате x y : ");
        String[] values = sc.nextLine().strip().split(" +");

        if (!(values.length ==2)) throw new IllegalArgumentException(" Необходимо вводить ровно 2 коодинаты");
        Coordinates coordinates = new Coordinates();
        coordinates.setX(Integer.parseInt(values[0]));
        coordinates.setY(Float.parseFloat(values[1]));
        this.rawFlat.setCoordinates(coordinates);

    }
    /**
     * Заполняет поле area объекта типа Flat
     */
    private void parceArea() throws IllegalArgumentException{
        System.out.print("Введите площадь: ");
        this.rawFlat.setArea(Integer.parseInt(sc.nextLine()));

    }
    /**
     * Заполняет поле numberOfRooms объекта типа Flat
     */
    private void parceNumberOfRooms() throws IllegalArgumentException{
        System.out.print("Введите количество комнат: ");
        this.rawFlat.setNumberOfRooms(Long.parseLong(sc.nextLine()));
    }
    /**
     * Заполняет поле furnish объекта типа Flat
     */
    private void parceFurnish() throws IllegalArgumentException{
        System.out.print("Введите Furish(DESIGNER,NONE,LITTLE): ");
        String input = sc.nextLine();
        this.rawFlat.setFurnish(Furnish.valueOf(input.strip()));
    }
    /**
     * Заполняет поле view объекта типа Flat
     */
    private void parceView(){
        System.out.print("Введите View(STREET,YARD,PARK,BAD,GOOD) : ");
        this.rawFlat.setView(View.valueOf(sc.nextLine().strip()));
    }
    /**
     * Заполняет поле transport объекта типа Flat
     */
    private void parceTransport()throws IllegalArgumentException{
        System.out.print("Введите Transport(NONE,LITTLE,NORMAL,ENOUGH): ");
        this.rawFlat.setTransport(Transport.valueOf(sc.nextLine().strip()));
    }
    /**
     * Заполняет поле house объекта типа Flat
     */
    private void parceHouse() throws IllegalArgumentException{
        /**
         * Класс для заполнения объекта типа House
         */
        class HouseParcer{
            House house;
            public HouseParcer() {
                house = new House();
            }
            /**
             * Заполняет поле name объекта типа House
             */
            void parceName(){
                System.out.print("Заполняем поле House - Введите имя Дома: ");
                House house = new House();
                house.setName(sc.nextLine());
            }
            /**
             * Заполняет поле year объекта типа House
             */
            void parceYear(){
                System.out.print("Заполняем поле House - Введите год : ");
                house.setYear(Integer.parseInt(sc.nextLine()));
            }
            /**
             * Заполняет поле numberOfFloors объекта типа House
             */
            void parceNumberOfFloors(){
                System.out.print("Заполняем поле House - Введите количество этажей : ");
                house.setNumberOfFloors(Integer.parseInt(sc.nextLine()));
            }
        }

        boolean filledName=false;
        boolean filledYear = false;
        boolean filledNumber = false;
        boolean done = false;

        HouseParcer h = new HouseParcer();
        while (!done){
            try {
                if (!filledName) h.parceName();
                filledName=true;

                if (!filledYear) h.parceYear();
                filledYear=true;

                if (!filledNumber) h.parceNumberOfFloors();
                filledNumber=true;
                done=true;
            }
            catch (IllegalArgumentException e){
                incorrectFieldNotifier(e);
            }
        }
        this.rawFlat.setHouse(h.house);


    }
    /**
     * Возвращает объект типа Flat
     * @return объект типа Flat
     */
    public Flat finnalizeObject(){

        doUntilDone(this::parceName,
                this::parceCoordinates,
                this::parceArea,
                this::parceNumberOfRooms,
                this::parceFurnish,
                this::parceView,
                this::parceTransport,
                this::parceHouse);

        return this.rawFlat;
    }
    /**
     * Выывает метод до те пор пока он не выполнится
     * @param runnables - методы
     */
    private void doUntilDone(Runnable ... runnables ){
        boolean done;
        for (Runnable r :
                runnables) {
            done = false;
            while (!done){
                try {
                    r.run();
                    done = true;
                } catch (IllegalArgumentException e) {
                    incorrectFieldNotifier(e);
                }
            }
        }
    }
    /**
     * Выводит сообщение об ошибке
     * @param e - исключение
     */
    private void incorrectFieldNotifier(IllegalArgumentException e){
        System.err.println("Вы введи недопустимый аргумент. "+ e.getMessage());
    }

}
