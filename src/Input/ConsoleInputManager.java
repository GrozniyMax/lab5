package Input;

import java.io.IOException;
import java.util.InputMismatchException;

import Entities.*;
import Exceptions.InvalidInputException;

public class ConsoleInputManager extends BaseInputManager implements InputManager {


    private static ConsoleInputManager instance;
    private ConsoleInputManager() {
        super(System.in);
    }

    public static ConsoleInputManager getInstance(){
        if (instance==null){
            instance = new ConsoleInputManager();
        }
        return instance;
    }

    /**
     * Читает координаты из консоли
     * @return Coordinates - прочтенные координаты
     */
    public Coordinates readCoordinates() throws InvalidInputException{
        try {
            System.out.print("Введите Координаты в формате x y : ");

            String[] values = this.readLine().strip().split(" +");
            if (!(values.length ==2)) throw new IllegalArgumentException(" Необходимо вводить ровно 2 коодинаты");
            Coordinates coordinates = new Coordinates();
            coordinates.setX(Integer.parseInt(values[0]));
            coordinates.setY(Float.parseFloat(values[1]));
            return coordinates;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    /**
     * Читает Furnish из консоли
     * @return Furnish - прочтенный Furnish
     */
    public Furnish readFurnish() throws InvalidInputException{
        try {
            System.out.print("Введите Furish (DESIGNER, NONE, LITTLE): ");
            return Furnish.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Не получилось распознать enum");
        }
    }

    /**
     * Читает View из консоли
     * @return View - прочтенный View
     */
    public View readView()throws InvalidInputException{
        try {
            System.out.print("Введите View (STREET, YARD, PARK, BAD, GOOD): ");
            return View.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Не получилось распознать enum");
        }
    }


    /**
     * Читает House из консоли в громком режиме
     * @return House - прочтенный House
     */
    public House readHouse(){
        House object = new House();
        this.doUntillCorrect(()->object.setName(this.readLine("Введите Имя дома: ")),
                ()->object.setYear(this.readInt("Введите год постройки: ")),
                ()->object.setNumberOfFloors(this.readInt("Введите количество этажей дома: "))
        );
        return object;
    }




    public Transport readTransport() throws InvalidInputException{
        try {
            System.out.print("Введите Transport (NONE, LITTLE, NORMAL, ENOUGH): ");
            return Transport.valueOf(this.readLine().strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }



    public Flat readFlat() {
        Flat object = new Flat();
        this.doUntillCorrect(()->object.setName(this.readLine("Введите Имя: ").strip()),
                ()->object.setCoordinates(this.readCoordinates()),
                ()->object.setArea(this.readInt("Введите площадь: ")),
                ()->object.setNumberOfRooms(this.readLong("Введите количество комнат: ")),
                ()->object.setFurnish(this.readFurnish()),
                ()->object.setView(this.readView()),
                ()->object.setTransport(this.readTransport()),
                ()->object.setHouse(this.readHouse())
        );
        return object;
    }



    /**
     * Выполняет действие пока не будет введено корректное значение
     * @param runnable - действие
     */
    public void doUntillCorrect(Runnable runnable){
        while (true){
            try {
                runnable.run();
                break;
            }catch (InvalidInputException|IllegalArgumentException e){
                System.err.println("Некорректный ввод: "+e.getMessage());
            }
        }
    }

    /**
     * Выполняет действия пока не будет введено корректное значение
     * @param runnables - действия
     */
    public void doUntillCorrect(Runnable ... runnables){
        for (Runnable runnable:runnables){
            doUntillCorrect(runnable);
        }
    }

}
