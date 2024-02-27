package Commands.CommandsWithInput;

import Commands.BaseCommand;
import Entities.Flat;
import Input.InputManager;

/**
 * Команда "add"
 */
public class Add extends BaseCommand implements CommandWithInput {
    /**
     * Конструктор команды
     * @see BaseCommand
     */
    public Add() {
        super("add",
                "добавляет элемент в коллекцию");
    }

    public void execute(CollectionWrappers.CollectionManager collectionManager, String argument, InputManager inputManager) throws IllegalArgumentException{
        Flat object = inputManager.readFlat();
        object.setId((long) collectionManager.getList().size());
        collectionManager.getList().add(object);
    }

}
