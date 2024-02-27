package Commands;

import CollectionWrappers.CollectionManager;

public interface CommandWithoutInput extends Command{
    public void execute(CollectionManager manager,String argument);

}
