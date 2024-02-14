import Entities.Flat;

import java.io.Console;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.io.Console;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    /**
     * Точка входа в программу
     * @param args - аргументы командной строки
     */
    public static void main(String[] args) {
        //TODO Протестировать команды

        String fileName = System.getenv("collectionFileName");
        if (fileName == null)  {
            System.out.println("Нужно запускать файл с переменной окружения " +
                    "'fileNameCollection' (названием файла, откуда брать коллекцию)!!!\n Будет использован базовый(~/dumping.json)");
            fileName = System.getProperty("user.dir") + "\\dumping.json";
        }

        JsonHandler.setFilePath(fileName);
        LinkedListManager manager=new LinkedListManager(new LinkedList<Flat>(), ZonedDateTime.now());
        try {
             manager = JsonHandler.load();
        } catch (IllegalArgumentException e) {
            System.err.println("В указаном файле в JSON есть ошибка");
        }
        catch (Throwable e){
            System.out.println("Поскольку что-то пошло не так, у нас не получилось распарсить коллекцию");
        }
        finally {
            runApp(new CommandHandler(manager));
        }
    }
    /**
     * Запуск программы
     * @param commandHandler - обработчик команд
     */
    public static void runApp(CommandHandler commandHandler){
        boolean exit = false;
        ConsoleManager manager = ConsoleManager.getInstance();
        while (!exit){
            exit = commandHandler.handle(manager.readLine(""));
        }

        
    }
}