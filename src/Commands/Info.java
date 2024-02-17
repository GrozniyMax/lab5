package Commands;

import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;
/**
 * Команда "info" - выводит информацию о коллекции
 */
public class Info extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public Info() {
        super("info","выводит информацию о коллекции","^\s*info\s*$");
    }
    /**
     * Реализация команды
     * @param manager - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager manager, Matcher matcher) {
        InputManager m = InputManager.getInstance();
        m.print("ДАТА СОЗДАНИЯ: "+ manager.creationDate.toString());
        m.print("ТИП КОЛЛЕКЦИИ: "+manager.getList().getClass().getSimpleName());
        m.print("КОЛИЧЕСТВО ЭЛЕМЕНТОВ: "+ manager.getList().size());
    }
}
