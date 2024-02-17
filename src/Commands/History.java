package Commands;

import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "history" - выводит последние 5 команд
 */
public class History extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public History() {
        super("history",
                "выводит последние 5 команд",
                "^\s*history\s*$" );
    }
    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        InputManager m = InputManager.getInstance();
        for (int i = collection.getHistoryIndex(); i < collection.getHistoryIndex()+5; i++) {
            if (collection.getHistory()[i%5]!=null) {
                m.print(collection.getHistory()[i % 5]);
            }
        }
    }
}
