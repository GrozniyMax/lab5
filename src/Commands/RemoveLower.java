package Commands;

import Entities.Flat;
import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "remove_lover". Удаляет все элементы меньше заявленного
 */
public class RemoveLower extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public RemoveLower() {
        super("remove_lover",
                "удаляет все элементы меньше заявленного",
                "^\s*remove_lower\s*$");
    }

    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        Flat compareObject = InputManager.getInstance().readFlat();
        collection.getList().removeIf((Flat f)->{
            return f.compareTo(compareObject) == -1;
        });

        for (int i = 0; i < collection.getList().size(); i++) {
            collection.getList().get(i).setId(Long.valueOf(i));
        }
    }
}
