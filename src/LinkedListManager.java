import Entities.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс для управления коллекцией
 */
public class LinkedListManager {

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
        System.out.println("Доступные команды:");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести информацию о коллекции");
        System.out.println("show : вывести все элементы коллекции");
        System.out.println("add : добавить новый элемент в коллекцию");
        System.out.println("update id : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("remove_first : удалить первый элемент из коллекции");
        System.out.println("remove_lower : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("remove_all_by_view view : удалить из коллекции все элементы, значение поля view которого эквивалентно заданному");
        System.out.println("group_counting_by_creation_date : сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе");
        System.out.println("count_greater_than_furnish furnish : вывести количество элементов, значение поля furnish которых больше заданного");
    }
    /**
     * Выводит информацию о коллекции
     */
    public void info(){
        System.out.println("ДАТА СОЗДАНИЯ: "+ creationDate.toString());
        System.out.println("ТИП КОЛЛЕКЦИИ: "+list.getClass().getSimpleName());
        System.out.println("КОЛИЧЕСТВО ЭЛЕМЕНТОВ: "+ list.size());
    }
    /**
     * Выводит все элементы коллекции
     */
    public void show() {
        list.stream().forEach((Flat e)->{System.out.println(e.toString());});
    }
    /**
     * Добавляет элемент в коллекцию
     */
    public void add(){
        Flat object = new ObjectFiller().finnalizeObject();
        object.setId(Long.valueOf(list.size()));
        list.add(object);
    }

    /**
     * Обновляет элемент коллекции
     * @param argument - id в виде строки
     */
    public void update(String argument) throws IndexOutOfBoundsException{
        Integer id = Integer.parseInt(argument.strip());
        list.set( id, new ObjectFiller().finnalizeObject());
    }
    /**
     * Удаляет элемент коллекции
     * @param argument - id в виде строки
     */
    public void removeById(String argument) throws IndexOutOfBoundsException {
        Integer id = Integer.parseInt(argument);
        list.remove(id);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(Long.valueOf(i));
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Выполняет скрипт
     * @param argument - путь к файлу скрипта
     */
    public void executeScript(String argument)  {
        CommandHandler commandHandler = new CommandHandler(this);

        argument = argument.strip();
        List<String> commands = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(argument))) {
            for (; ; ) {
                String line = reader.readLine();
                if (line == null)
                    break;
                commands.add(line);

            }
            for (String command :
                    commands) {
                commandHandler.handle(command);
            }
        } catch (IOException e) {
            System.out.println("Прочитать файл не удалось");
        }
    }
    /**
     * Удаляет первый элемент коллекции
     */
    public void removeFirst() {
        list.removeFirst();
    }
    /**
     * Удаляет элементы, меньшие, чем заданный
     */
    public void removeLower() {
        for (Flat listObj :
                list) {
            if (listObj.compareTo(new ObjectFiller().finnalizeObject()) == -1) {
                list.remove(listObj);
            }
        }
    }
    /**
     * Удаляет элементы по значению поля view
     * @param argument - view в виде строки
     */
    public void removeAllByView(String argument) throws IllegalArgumentException{
        View view = View.valueOf(argument.strip().toUpperCase());
        for (Flat listObj :
                list) {
            if (listObj.getView().equals(view)) {
                list.remove(listObj);
            }
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

        for (ZonedDateTime time: dates.keySet()) {
            System.out.println(time.toString()+" : "+dates.get(time).toString());
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
            if (f.getFurnish()== furish){
                counter++;
            }
        }
        System.out.println("Количество объектов с таким полем Furish" + counter);
    }



}


