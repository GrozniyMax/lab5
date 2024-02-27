package Input;

import Entities.*;
import Utils.Pair;

public interface InputManager {
    //TODO ��������� ��� ������� ������ (readFlat etc)
    String readLine();

    Coordinates readCoordinates();

     Furnish readFurnish();

     View readView();

     Transport readTransport();

     House readHouse();

     Flat readFlat();

}
