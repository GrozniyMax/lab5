package Commands;

import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "remove_first". Удаляет первый элемент.
 */
public class RemoveFirst extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public RemoveFirst() {
        super("remove_first",
                "удаляет первый элемент",
                "^\s*remove_first\s*$" );
    }
    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, который содержит id элемента
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        collection.getList().removeFirst();
        for (int i = 0; i < collection.getList().size(); i++) {
            collection.getList().get(i).setId(Long.valueOf(i));
        }
    }
}
