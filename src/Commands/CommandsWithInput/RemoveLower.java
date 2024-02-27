package Commands.CommandsWithInput;

import Commands.BaseCommand;
import Entities.Flat;
import Input.InputManager;

/**
 * Команда "remove_lover". Удаляет все элементы меньше заявленного
 */
public class RemoveLower extends BaseCommand implements CommandWithInput {
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public RemoveLower() {
        super("remove_lover",
                "удаляет все элементы меньше заявленного");
    }

    @Override
    public void execute(CollectionWrappers.CollectionManager collectionManager, String argument, InputManager inputManager) {
        Flat compareObject = inputManager.readFlat();
        collectionManager.getList().removeIf((Flat f)->{
            return f.compareTo(compareObject) == -1;
        });

        for (int i = 0; i < collectionManager.getList().size(); i++) {
            collectionManager.getList().get(i).setId((long) i);
        }
    }
}
