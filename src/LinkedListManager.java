import Entities.Flat;
import Entities.Furnish;
import Entities.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Класс для управления коллекцией
 */
public class LinkedListManager {
    //TODO синхронизация ID в остальных функциях

    private LinkedList<Flat> list;

    final public ZonedDateTime creationDate;




    /**
     * Конструктор
     * @param list - коллекция
     * @param creationDate - дата создания
     */
    public LinkedListManager(LinkedList<Flat> list, ZonedDateTime creationDate) {
        this.list = list;
        this.creationDate = creationDate;
    }

    public LinkedList<Flat> getList() {
        return list;
    }

    /**
     * Выводит справку по командам
     */
    public void help(){
        InputManager m = InputManager.getInstance();
        m.print("ОЧЕНЬ ВАЖНО: Вводить поля Flat в следующем порядке: name, coordinates, area, numberOfRooms, furnish, view, transport, house");
        m.print("Поля house вводить в следующем порядке: name, year, numberOfFloors, numberOfFloors");
        m.print("Все поля вводятся по одному в строку");
        m.print("Доступные команды:");
        m.print("help : вывести справку по доступным командам");
        m.print("info : вывести информацию о коллекции");
        m.print("show : вывести все элементы коллекции");
        m.print("add : добавить новый элемент в коллекцию");
        m.print("update id : обновить значение элемента коллекции, id которого равен заданному");
        m.print("remove_by_id id : удалить элемент из коллекции по его id");
        m.print("clear : очистить коллекцию");
        m.print("save : сохранить коллекцию в файл");
        m.print("execute_script file_name : считать и исполнить скрипт из указанного файла");
        m.print("exit : завершить программу (без сохранения в файл)");
        m.print("remove_first : удалить первый элемент из коллекции");
        m.print("remove_lower : удалить из коллекции все элементы, меньшие, чем заданный");
        m.print("remove_all_by_view view : удалить из коллекции все элементы, значение поля view которого эквивалентно заданному");
        m.print("group_counting_by_creation_date : сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе");
        m.print("count_greater_than_furnish furnish : вывести количество элементов, значение поля furnish которых больше заданного");
    }
    /**
     * Выводит информацию о коллекции
     */
    public void info(){
        InputManager m = InputManager.getInstance();
        m.print("ДАТА СОЗДАНИЯ: "+ creationDate.toString());
        m.print("ТИП КОЛЛЕКЦИИ: "+list.getClass().getSimpleName());
        m.print("КОЛИЧЕСТВО ЭЛЕМЕНТОВ: "+ list.size());
    }
    /**
     * Выводит все элементы коллекции
     */
    public void show() {
        InputManager m = InputManager.getInstance();
        list.stream().forEach((Flat e)->{m.print(e.toString());});
    }
    /**
     * Добавляет элемент в коллекцию
     */
    public void add(){
        Flat object = InputManager.getInstance().readFlat();
        object.setId(Long.valueOf(list.size()));
        list.add(object);
    }

    /**
     * Обновляет элемент коллекции
     * @param argument - id в виде строки
     */
    public void update(String argument) throws IllegalArgumentException{
        try {
            Integer id = Integer.parseInt(argument.strip());
            list.set( id,
                    InputManager.getInstance().readFlat());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("id больше чем количество элементов массива");
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        }
    }
    /**
     * Удаляет элемент коллекции
     * @param argument - id в виде строки
     */
    public void removeById(String argument) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(argument.strip());
            list.remove(id);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setId(Long.valueOf(i));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("id больше чем количество элементов массива");
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        }
    }
    /**
     * Очищает коллекцию
     */
    public void clear() {
        list = new LinkedList();
    }
    /**
     * Сохраняет коллекцию
     */
    public void save() {
        try {
            JsonHandler.dump(this);
            InputManager.getInstance().print("Коллекция сохранена");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Выполняет скрипт
     * @param argument - путь к файлу скрипта
     */
    public void executeScript(String argument) throws FileNotFoundException {
        CommandHandler commandHandler = new CommandHandler(this);
        InputManager.setСustomStreams(new FileInputStream(argument.strip()),
                System.out,
                System.err);
        InputManager.setSilent(true);
        boolean exit = false;
        InputManager manager = InputManager.getInstance();
        try {
            while (!exit){
                exit = commandHandler.handle(manager.readCommand());
            }
            manager.print("Скрипт выполнен успешно");
        } catch (Throwable e) {
            manager.printError(new Throwable("Ошибка во время выполнения скрипта"));
        }
        finally {
            InputManager.setСustomStreams(System.in,
                    System.out,
                    System.err);
            InputManager.setSilent(false);
        }
    }
    /**
     * Удаляет первый элемент коллекции
     */
    public void removeFirst() {
        list.removeFirst();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(Long.valueOf(i));
        }
    }
    /**
     * Удаляет элементы, меньшие, чем заданный
     */
    public void removeLower() {
        Flat compareObject = InputManager.getInstance().readFlat();
        for (Flat listObj :
                list) {
            if (listObj.compareTo(compareObject) == -1) {
                list.remove(listObj);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(Long.valueOf(i));
        }
    }
    /**
     * Удаляет элементы по значению поля view
     * @param argument - view в виде строки
     */
    public void removeAllByView(String argument) throws IllegalArgumentException{
        View view = View.valueOf(argument.strip().toUpperCase());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getView().equals(view)){
                list.remove(i);
                i--;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(Long.valueOf(i));
        }
    }
    /**
     * Группирует элементы по значению поля creationDate
     */
    public void groupCountingByCreationDate() {
        HashMap<ZonedDateTime,Integer> dates = new HashMap<>();
        ZonedDateTime currentDate;
        for (Flat flat : list) {
            currentDate = flat.getCreationDate();
            if (dates.containsKey(currentDate)){
                dates.put(currentDate,dates.get(currentDate)+1);
            }
            else {
                dates.put(currentDate,1);
            }
        }
        InputManager m = InputManager.getInstance();
        for (ZonedDateTime time: dates.keySet()) {
            m.print(time.toString()+" : "+dates.get(time).toString());
        }
        
    }

    /**
     * Выводит количество элементов, значение поля furnish которых больше заданного
     * @param argument - furnish в виде строки
     */
    public void countGreaterThanFurish(String argument) throws IllegalArgumentException{
        Furnish furish = Furnish.valueOf(argument.strip().toUpperCase());

        long counter=0;

        for (Flat f : list ) {
            if (f.getFurnish().compareTo(furish)>0){
                counter++;
            }
        }
        InputManager.getInstance().print("Количество объектов с таким полем Furish " + counter);
    }
}


