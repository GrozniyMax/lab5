package Commands.CommandsWithInput;

import CollectionWrappers.CollectionManager;
import Commands.BaseCommand;
import Input.InputManager;

/**
 * Команда "update". Обновляет данные элемента коллекции по id
 */
public class Update extends BaseCommand implements CommandWithInput {
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public Update() {
        super("update",
                "обновляет данные элемента коллекции по id");
    }

    @Override
    public void execute(CollectionManager collectionManager, String argument, InputManager inputManager) {
        try {
            Integer id = Integer.parseInt(argument);
            collectionManager.getList().set( id,
                    inputManager.readFlat());
        } catch ( NumberFormatException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(" Указанный id больше чем количество элементов массива");
        }
    }
}
