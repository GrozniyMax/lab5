import Entities.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для управления консолью
 */
public class InputManager {
    //TODO Добавить обработку файла(execute_file)
    private static InputManager manager;
    private BufferedReader reader;
    private PrintStream printStream;
    private PrintStream errorStream;
    private static boolean isSilent = false;

    static {
        //Инициализируем классический менеджер консоли
        manager = new InputManager(System.in,System.out,System.err);
    }
    /**
     * Конструктор
     * @param readFrom - поток ввода
     * @param printTo - поток вывода
     * @param errStream - поток вывода ошибок
     */
    private InputManager(InputStream readFrom, PrintStream printTo, PrintStream errStream) {
        this.printStream = printTo;
        this.errorStream = errStream;
        this.reader = new BufferedReader(new InputStreamReader(readFrom));
    }

    public static void setSilent(boolean silent) {
        isSilent = silent;
    }

    /**
     * Устанавливает потоки ввода/вывода
     * @param readFrom - поток ввода
     * @param printTo - поток вывода
     * @param errStream - поток вывода ошибок
     */
    public static void setСustomStreams(InputStream readFrom, PrintStream printTo, PrintStream errStream){
        manager = new InputManager(readFrom,printTo,errStream);
    }

    /**
     * Возвращает экземпляр менеджера консоли
     * @return экземпляр менеджера консоли
     */
    public static InputManager getInstance(){
        return Objects.requireNonNull(manager,"You need to setStreams first");
    }

    /**
     * Выводит объект в консоль
     * @param content - объект
     */
    public void print(Object content) {
        printStream.println(content);
    }

    /**
     * Выводит объект в консоль
     * @param content - объект
     * @param end - окончание строки
     */
    public void print(Object content,String end) {
        printStream.print(content);
        printStream.print(end);
    }
    /**
     * Выводит ошибку в консоль
     * @param e - ошибка
     */
    public void printError(Throwable e){
        errorStream.println("Произошла непридвиденная ошибка. "+e.getMessage());
    }

    /**
     * Выводит ошибку в консоль
     * @param e - ошибка
     */
    public void printError(IllegalArgumentException e){
        printStream.println("Произошла ошибка. "+e.getMessage());
    }
    public void printError(NullPointerException e){
        printStream.println("Произошла ошибка. "+e.getMessage());
    }

    /**
     * Читает строку из консоли
     * @return строка
     */
    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения вводимых данных, это критическая ошибка");
        }
    }
    /**
     * Читает строку из консоли добавляя префикс
     * @param prefix - префикс
     * @return строка
     */
    public String readLine(String prefix) {
        this.print(prefix,"");
        return this.readLine();
    }

    /**
     * Читает целое число из консоли
     * @return Integer - прочтенное число
     */
    public Integer readInt() {
        return Integer.parseInt(this.readLine().strip());
    }
    /**
     * Читает целое число из консоли добавляя префикс
     * @param prefix - префикс
     * @return Integer - прочтенное число
     */
    public Integer readInt(String prefix){
        printStream.print(prefix);
        return Integer.parseInt(this.readLine().strip());
    }

    /**
     * Читает длинное число из консоли
     * @return Long - прочтенное число
     */
    public Long readLong() {
        return Long.parseLong(this.readLine().strip());
    }
    /**
     * Читает длинное число из консоли добавляя префикс
     * @param prefix - префикс
     * @return Long - прочтенное число
     */
    public Long readLong(String prefix){
        printStream.print(prefix);
        return Long.parseLong(this.readLine().strip());
    }
    /**
     * Читает дробное число из консоли
     * @return Float - прочтенное число
     */
    public Float readFloat() {
        return Float.parseFloat(this.readLine().strip());
    }
    /**
     * Читает дробное число из консоли добавляя префикс
     * @param prefix - префикс
     * @return Float - прочтенное число
     */
    public Float readFloat(String prefix){
        printStream.print(prefix);
        return Float.parseFloat(this.readLine().strip());
    }
    /**
     * Читает координаты из консоли
     * @return Coordinates - прочтенные координаты
     */
    public Coordinates readCoordinates() throws IllegalArgumentException{
        System.out.print("Введите Координаты в формате x y : ");
        String[] values = this.readLine().strip().split(" +");
        if (!(values.length ==2)) throw new IllegalArgumentException(" Необходимо вводить ровно 2 коодинаты");
        Coordinates coordinates = new Coordinates();
        coordinates.setX(Integer.parseInt(values[0]));
        coordinates.setY(Float.parseFloat(values[1]));
        return coordinates;
    }
    /**
     * Читает координаты из консоли добавляя префикс
     * @param prefix - префикс
     * @return Coordinates - прочтенные координаты
     */
    public Coordinates readCoordinates(String prefix){
        printStream.print(prefix);
        return this.readCoordinates();
    }
    /**
     * Читает Furnish из консоли
     * @return Furnish - прочтенный Furnish
     */
    public Furnish readFurish(){
        return Furnish.valueOf(this.readLine().strip());
    }
    public Furnish readFurish(String prefix){
        this.print(prefix,"");
        return this.readFurish();
    }

    /**
     * Читает View из консоли
     * @return View - прочтенный View
     */
    public View readView(){
        return View.valueOf(this.readLine().strip());
    }

    public View readView(String prefix){
        this.print(prefix,"");
        return View.valueOf(this.readLine().strip());
    }
    /**
     * Читает House из консоли
     * @return House - прочтенный House
     */
    private House readHouseLoud(){
        House object = new House();
        this.doUntillCorrect(()->object.setName(this.readLine("Введите Имя дома: ")),
                ()->object.setYear(this.readInt("Введите год постройки: ")),
                ()->object.setNumberOfFloors(this.readInt("Введите количество этажей дома: "))
        );
        return object;
    }

    private House readHouseSilent()throws IllegalArgumentException{
        House object = new House();
        object.setName(this.readLine());
        object.setYear(this.readInt());
        object.setNumberOfFloors(this.readInt());
        return object;
    }

    public House readHouse() throws IllegalArgumentException{
        if (isSilent) return readHouseSilent();
        return readHouseLoud();
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
            }catch (IllegalArgumentException e){
                this.printError(e);
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

    public Transport readTransport(){
        return Transport.valueOf(this.readLine().strip());
    }

    /**
     * Читает Transport из консоли
     * @return Transport - прочтенный Transport
     */
    public Transport readTransport(String prefix){
        this.print(prefix,"");
        return this.readTransport();
    }

    /**
     * Читает Flat из консоли
     * @return Flat - прочтенный Flat
     */
    private Flat readFlatLoud() {
        Flat object = new Flat();
        this.doUntillCorrect(()->object.setName(this.readLine("Введите Имя: ")),
                ()->object.setCoordinates(this.readCoordinates()),
                ()->object.setArea(this.readInt("Введите площадь: ")),
                ()->object.setNumberOfRooms(this.readLong("Введите количество комнат: ")),
                ()->object.setFurnish(this.readFurish("Введите Furish (DESIGNER, NONE, LITTLE): ")),
                ()->object.setView(this.readView("Введите View (STREET, YARD, PARK, BAD, GOOD): ")),
                ()->object.setTransport(this.readTransport("Введите Transport (NONE, LITTLE, NORMAL, ENOUGH): ")),
                ()->object.setHouse(this.readHouse())
        );
        return object;
    }

    private Flat readFlatSilent() throws IllegalArgumentException{
        Flat object = new Flat();
        object.setName(this.readLine());
        object.setCoordinates(this.readCoordinates());
        object.setArea(this.readInt());
        object.setNumberOfRooms(this.readLong());
        object.setFurnish(this.readFurish());
        object.setView(this.readView());
        object.setTransport(this.readTransport());
        object.setHouse(this.readHouse());
        return object;
    }

    public Flat readFlat(){
        if (isSilent) return readFlatSilent();
        return readFlatLoud();
    }


    public Pair<Command,Matcher> readCommand() throws IllegalArgumentException,NullPointerException{
        String line = Objects.requireNonNull(this.readLine(),"EOF");
        for (Command command:
                Command.values()) {
            Matcher matcher = command.getPattern().matcher(line);
            if (matcher.matches()) return new Pair<>(command,matcher);
        }
        throw new IllegalArgumentException("Команда не распознана");
    }

    // TODO Протестировать

}
