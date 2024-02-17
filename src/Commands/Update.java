package Commands;

import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;
/**
 * Команда "update". Обновляет данные элемента коллекции по id
 */
public class Update extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public Update() {
        super("update",
                "обновляет данные элемента коллекции по id",
                "^\s*update\s+(\\d+)$");
    }
    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) throws IllegalArgumentException{
        try {
            Integer id = Integer.parseInt(matcher.group(1).strip());
            collection.getList().set( id,
                    InputManager.getInstance().readFlat());
        } catch (IllegalStateException | NumberFormatException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        } catch (IndexOutOfBoundsException e) {
            if (e.getMessage().contains("No group")) throw new IllegalArgumentException("Некорректно введенный id");
            throw new IllegalArgumentException(" Указанный id больше чем количество элементов массива");
        }
    }
}
