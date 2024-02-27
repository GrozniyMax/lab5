package Commands;

import CollectionWrappers.CollectionManager;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 * ������� "history" - ������� ��������� 5 ������
 */
public class History extends BaseCommand implements CommandWithoutInput{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public History() {
        super("history",
                "������� ��������� 5 ������");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        manager.getHistory().forEach(System.out::println);
    }
}
