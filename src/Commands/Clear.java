package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;

/**
 * Команда очистки коллекции
 */
public class Clear extends BaseCommand implements CommandWithoutInput{
    /**
     * Конструктор без параметров
     * @see BaseCommand
     */
    public Clear() {
        super("clear",
                "очишщает коллекцию");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        manager.getList().clear();
    }
}
