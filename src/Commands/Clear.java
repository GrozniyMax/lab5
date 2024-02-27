package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;

/**
 * ������� ������� ���������
 */
public class Clear extends BaseCommand implements CommandWithoutInput{
    /**
     * ����������� ��� ����������
     * @see BaseCommand
     */
    public Clear() {
        super("clear",
                "�������� ���������");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        manager.getList().clear();
    }
}
