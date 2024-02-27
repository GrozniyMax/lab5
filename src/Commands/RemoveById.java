package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;


/**
 * Команда "remove_by_id". Удаляет элемент из коллекции по его id.
 */
public class RemoveById extends BaseCommand implements CommandWithoutInput{
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public RemoveById() {
        super("remove_by_id","удаляет объект по id");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        try {
            Integer id = Integer.parseInt(argument.strip());
            manager.getList().remove(id);
            for (int i = 0; i < manager.getList().size(); i++) {
                manager.getList().get(i).setId(Long.valueOf(i));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("id больше чем количество элементов массива");
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        }
    }
}
