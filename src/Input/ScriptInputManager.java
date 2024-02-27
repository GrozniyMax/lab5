package Input;

import Entities.*;
import Exceptions.EndOfStreamException;
import Exceptions.InvalidInputException;
import Utils.Pair;

import java.io.EOFException;
import java.io.InputStream;

public class ScriptInputManager extends BaseInputManager implements InputManager{
    //TODO протестировать на незаконченном вводе при команде  add

    public ScriptInputManager(InputStream readFrom) {
        super(readFrom);
    }



    public Coordinates readCoordinates() throws InvalidInputException{
        try {
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


    public Furnish readFurnish() throws InvalidInputException{
        try {
            return Furnish.valueOf(this.readLine().strip());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }



    public View readView() throws InvalidInputException{
        try {
            return View.valueOf(this.readLine().strip());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }


    public House readHouse()throws InvalidInputException{
        try {
            House object = new House();
            object.setName(this.readLine());
            object.setYear(this.readInt());
            object.setNumberOfFloors(this.readInt());
            return object;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }


    public Transport readTransport() throws InvalidInputException{
        try {
            return Transport.valueOf(this.readLine().strip());
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }


    public Flat readFlat() throws InvalidInputException{
        try {
            Flat object = new Flat();
            object.setName(this.readLine());
            object.setCoordinates(this.readCoordinates());
            object.setArea(this.readInt());
            object.setNumberOfRooms(this.readLong());
            object.setFurnish(this.readFurnish());
            object.setView(this.readView());
            object.setTransport(this.readTransport());
            object.setHouse(this.readHouse());
            return object;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

}
