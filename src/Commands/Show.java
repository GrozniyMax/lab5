package Commands;

import Entities.Flat;
import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "show" - выводит все элементы коллекции
 */
public class Show extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public Show() {
        super("show","выводит все элементы коллекции","^\s*show\s*$");
    }
    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        InputManager m = InputManager.getInstance();
        if (collection.getList().size()==0){
            m.print("Коллекция пустая");
        }
        else {
            collection.getList().stream().forEach((Flat e) -> {
                m.print(e.toString());
            });
        }
    }
}
