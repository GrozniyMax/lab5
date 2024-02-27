package Commands.CommandsWithInput;

import CollectionWrappers.CollectionManager;
import Commands.Command;
import Input.InputManager;

import java.io.Reader;

public interface CommandWithInput extends Command {

    public void execute(CollectionManager collectionManager, String argument, InputManager inputManager);
}
