package Commands;

import Managers.InputManager;
import Managers.LinkedListManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;

/**
 * Класс команды выполнения скрипта
 */
public class ExecuteScript extends Command{
    /**
     * Конструктор класса команды
     * @see Command
     */
    public ExecuteScript() {
        super("executeScript",
                "выполняет скрипт записанный в файл(путь передается в виде аргумента функции)",
                "^\s*execute_script\s+(.+)$");
    }
    /**
     * Обработчик команды
     * @param collection - менеджер коллекции
     * @param matcher - аргументы команды
     * @throws IllegalArgumentException - исключение
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) throws IllegalArgumentException {

        try {
            String argument = matcher.group(1).strip();
            InputManager.setСustomStreams(new FileInputStream(matcher.group(1).strip()),
                    System.out,
                    System.err);
            InputManager.setSilent(true);
            boolean exit = false;
            while (!exit) {
                exit = collection.handle(InputManager.getInstance().readCommand(collection.getCommands()));
            }
            InputManager.getInstance().print("Скрипт выполнен успешно");
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Указаный файл отсутсвует");
        }catch (NullPointerException e){
            //do nothing;
        }catch (Throwable e) {
            InputManager.getInstance().printError(new Throwable("Ошибка во время выполнения скрипта "));
        }
        finally {
            InputManager.setСustomStreams(System.in,
                    System.out,
                    System.err);
            InputManager.setSilent(false);
        }
    }
}
