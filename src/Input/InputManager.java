package Input;

import Entities.*;
import Utils.Pair;

public interface InputManager {
    //TODO прописать все сложные методы (readFlat etc)
    String readLine();

    Coordinates readCoordinates();

     Furnish readFurnish();

     View readView();

     Transport readTransport();

     House readHouse();

     Flat readFlat();

}
