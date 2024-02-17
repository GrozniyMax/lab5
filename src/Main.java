import Entities.Flat;
import Managers.InputManager;
import Managers.JsonManager;
import Managers.LinkedListManager;

import java.nio.file.FileSystems;
import java.nio.file.Path;
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

        String fileName = System.getenv("collectionFileName");

        if (fileName == null)  {
            System.out.println("Нужно запускать файл с переменной окружения " +
                    "'fileNameCollection' (названием файла, откуда брать коллекцию)!!!\n Будет использован базовый(~/dumping.json)");
            fileName = "dumping.json";
        }
        //Приводим путь к абсолютному виду
        fileName = FileSystems.getDefault().getPath(fileName).normalize().toAbsolutePath().toString();
        JsonManager.setFilePath(fileName);
        LinkedListManager manager=new LinkedListManager(new LinkedList<Flat>(), ZonedDateTime.now());
        try {
             manager = JsonManager.load();
        } catch (IllegalArgumentException e) {
            System.err.println("В указаном файле в JSON есть ошибка");
        }
        catch (Throwable e){
            System.out.println("Поскольку что-то пошло не так, у нас не получилось распарсить коллекцию");
        }
        finally {
            runApp(manager);
        }
    }
    /**
     * Запуск программы
     * @param commandHandler - обработчик команд
     */
    public static void runApp(LinkedListManager linkedListManager){
        boolean exit = false;
        InputManager manager = InputManager.getInstance();
        while (!exit){
            try {
                exit = linkedListManager.handle(manager.readCommand(linkedListManager.getCommands()));
            }catch (IllegalArgumentException e){
                InputManager.getInstance().printError(e);
            }
        }
    }
}