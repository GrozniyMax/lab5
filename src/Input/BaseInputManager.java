package Input;

import Exceptions.EndOfStreamException;
import Exceptions.InvalidInputException;

import java.io.*;

/**
 * ����� ��� ���������� ������/�������
 */
public class BaseInputManager {
    //TODO ���������� ��� ���, ��� ����� �������� ������� ������, � ��� ������� ������ � �����������
    //TODO ������� ���� Output � ��������� ����� � ������� ��� ���� InputManager...
    protected static BaseInputManager manager;
    protected BufferedReader reader;
    protected BaseInputManager(InputStream readFrom) {
        this.reader = new BufferedReader(new InputStreamReader(readFrom));
    }


    /**
     * ������ ������ �� �������
     * @return ������
     */
    public String readLine() throws EndOfStreamException{
        try {
            String line = reader.readLine();
            if (line==null) throw new EndOfStreamException();
            return line;
        } catch (IOException e) {
            throw new RuntimeException("������ ������ �������� ������, ��� ����������� ������");
        }
    }
    /**
     * ������ ������ �� ������� �������� �������
     * @param prefix - �������
     * @return ������
     */
    public String readLine(String prefix) throws EndOfStreamException {
        System.out.print(prefix);
        return this.readLine();
    }

    /**
     * ������ ����� ����� �� �������
     * @return Integer - ���������� �����
     */
    public Integer readInt() throws InvalidInputException {
        try {
            return Integer.parseInt(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("������������ ���� ������ ����� ������� Integer");
        }
    }
    /**
     * ������ ����� ����� �� ������� �������� �������
     * @param prefix - �������
     * @return Integer - ���������� �����
     */
    public Integer readInt(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return this.readInt();
    }

    /**
     * ������ ������� ����� �� �������
     * @return Long - ���������� �����
     */
    public Long readLong() throws InvalidInputException {
        try {
            return Long.parseLong(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("������������ ������� ����� ������� Long");
        }
    }
    /**
     * ������ ������� ����� �� ������� �������� �������
     * @param prefix - �������
     * @return Long - ���������� �����
     */
    public Long readLong(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return this.readLong();
    }
    /**
     * ������ ������� ����� �� �������
     * @return Float - ���������� �����
     */
    public Float readFloat() throws InvalidInputException {
        try {
            return Float.parseFloat(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("������������ ������� ����� ������� Float");
        }
    }
    /**
     * ������ ������� ����� �� ������� �������� �������
     * @param prefix - �������
     * @return Float - ���������� �����
     */
    public Float readFloat(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return readFloat();
    }
}
