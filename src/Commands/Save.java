package Commands;

import CollectionWrappers.CollectionManager;
import Input.BaseInputManager;
import Managers.JsonManager;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
/**
 * ������� "save" - ��������� ��������� � ����
 */
public class Save extends BaseCommand implements CommandWithoutInput{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Save() {
        super("save",
                "��������� ��������� � ����");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        try {
            JsonManager.dump(manager.getCollection());
            System.out.println("��������� ���������");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
