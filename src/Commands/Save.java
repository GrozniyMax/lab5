package Commands;

import Commands.Command;
import Managers.InputManager;
import Managers.JsonManager;
import Managers.LinkedListManager;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
/**
 * Команда "save" - сохраняет коллекцию в файл
 */
public class Save extends Command {
    /**
     * Пустой конструктор
     * @see Command
     */
    public Save() {
        super("save",
                "сохраняет коллекцию в файл",
                "^\s*save\s*$");
    }
    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        try {
            JsonManager.dump(collection);
            InputManager.getInstance().print("Коллекция сохранена");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
