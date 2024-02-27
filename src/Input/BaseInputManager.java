package Input;

import Exceptions.EndOfStreamException;
import Exceptions.InvalidInputException;

import java.io.*;

/**
 * Класс для управления вводом/выводом
 */
public class BaseInputManager {
    //TODO Переписать все так, что здесь остаются базовые методы, а все сложные уходят в наследников
    //TODO Вынести весь Output в отдельный класс и указать как поле InputManager...
    protected static BaseInputManager manager;
    protected BufferedReader reader;
    protected BaseInputManager(InputStream readFrom) {
        this.reader = new BufferedReader(new InputStreamReader(readFrom));
    }


    /**
     * Читает строку из консоли
     * @return строка
     */
    public String readLine() throws EndOfStreamException{
        try {
            String line = reader.readLine();
            if (line==null) throw new EndOfStreamException();
            return line;
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения вводимых данных, это критическая ошибка");
        }
    }
    /**
     * Читает строку из консоли добавляя префикс
     * @param prefix - префикс
     * @return строка
     */
    public String readLine(String prefix) throws EndOfStreamException {
        System.out.print(prefix);
        return this.readLine();
    }

    /**
     * Читает целое число из консоли
     * @return Integer - прочтенное число
     */
    public Integer readInt() throws InvalidInputException {
        try {
            return Integer.parseInt(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Некорректный ввод целого числа формата Integer");
        }
    }
    /**
     * Читает целое число из консоли добавляя префикс
     * @param prefix - префикс
     * @return Integer - прочтенное число
     */
    public Integer readInt(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return this.readInt();
    }

    /**
     * Читает длинное число из консоли
     * @return Long - прочтенное число
     */
    public Long readLong() throws InvalidInputException {
        try {
            return Long.parseLong(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Неккорректно введено число формата Long");
        }
    }
    /**
     * Читает длинное число из консоли добавляя префикс
     * @param prefix - префикс
     * @return Long - прочтенное число
     */
    public Long readLong(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return this.readLong();
    }
    /**
     * Читает дробное число из консоли
     * @return Float - прочтенное число
     */
    public Float readFloat() throws InvalidInputException {
        try {
            return Float.parseFloat(this.readLine().strip());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Неккорректно введено число формата Float");
        }
    }
    /**
     * Читает дробное число из консоли добавляя префикс
     * @param prefix - префикс
     * @return Float - прочтенное число
     */
    public Float readFloat(String prefix) throws InvalidInputException {
        System.out.print(prefix);
        return readFloat();
    }
}
