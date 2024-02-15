import Entities.Flat;

import java.time.ZonedDateTime;
import java.util.LinkedList;

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
        InputManager manager = InputManager.getInstance();
        while (!exit){
            try {
                exit = commandHandler.handle(manager.readCommand());
            }catch (IllegalArgumentException e){
                InputManager.getInstance().printError(e);
            }
        }
    }
}