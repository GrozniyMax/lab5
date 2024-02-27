package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;

/**
 * Команда "remove_first". Удаляет первый элемент.
 */
public class RemoveFirst extends BaseCommand implements CommandWithoutInput{
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public RemoveFirst() {
        super("remove_first",
                "удаляет первый элемент");
    }

    @Override
    public void execute(CollectionManager manager, String argument) {
        manager.getList().remove(0);
        for (int i = 0; i < manager.getList().size(); i++) {
            manager.getList().get(i).setId((long) i);
        }
    }
}
