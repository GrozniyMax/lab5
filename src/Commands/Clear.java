package Commands;

import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда очистки коллекции
 */
public class Clear extends Command{
    /**
     * Конструктор без параметров
     * @see Command
     */
    public Clear() {
        super("clear",
                "очишщает коллекцию",
                "^\s*clear\s*$");
    }
    /**
     * Выполнение команды
     * @param collection - менеджер коллекции
     * @param matcher - объект класса Matcher
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        collection.getList().clear();
    }
}
