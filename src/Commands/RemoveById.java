package Commands;

import Managers.LinkedListManager;

import java.util.regex.Matcher;


/**
 * Команда "remove_by_id". Удаляет элемент из коллекции по его id.
 */
public class RemoveById extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public RemoveById() {
        super("remove_by_id","удаляет объект по id","^\s*remove_by_id\s+(\\d+)$");
    }

    /**
     * Удаляет элемент из коллекции по его id.
     * @param collection - менеджер коллекции
     * @param matcher - Matcher, который содержит id элемента
     * @throws IllegalArgumentException - если id некорректный
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) throws IllegalArgumentException{
        try {
            Integer id = Integer.parseInt(matcher.group(1).strip());
            collection.getList().remove(id);
            for (int i = 0; i < collection.getList().size(); i++) {
                collection.getList().get(i).setId(Long.valueOf(i));
            }
        } catch (IndexOutOfBoundsException e) {
            if (e.getMessage().contains("No group")) throw new IllegalArgumentException("Некорректно введенный id");
            throw new IllegalArgumentException("id больше чем количество элементов массива");
        }catch (NumberFormatException|IllegalStateException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        }
    }
}
