package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;

/**
 * ������� "remove_first". ������� ������ �������.
 */
public class RemoveFirst extends BaseCommand implements CommandWithoutInput{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public RemoveFirst() {
        super("remove_first",
                "������� ������ �������");
    }

    @Override
    public void execute(CollectionManager manager, String argument) {
        manager.getList().remove(0);
        for (int i = 0; i < manager.getList().size(); i++) {
            manager.getList().get(i).setId((long) i);
        }
    }
}
