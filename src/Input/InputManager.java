package Input;

import Entities.*;
import Utils.Pair;

/**
 * ��������� ��� ���������� ������
 */
public interface InputManager {

    /**
     * ������ ������ �� �������
     * @return ������
     */
    String readLine();

    /**
     * ������ ���������� �� �������
     * @return ����������
     */
    Coordinates readCoordinates();


    /**
     * ������ Furnish �� �������
     * @return ����������� Furnish
     */
    Furnish readFurnish();

    /**
     * ������ View �� �������
     * @return ����������� View
     */
     View readView();

    /**
     * ������ Transport �� �������
     * @return ����������� Transport
     */

     Transport readTransport();

    /**
     * ������ House �� �������
     * @return ����������� House
     */
    House readHouse();

    /**
     * ������ Flat �� �������
     * @return ����������� Flat
     */
     Flat readFlat();

}
