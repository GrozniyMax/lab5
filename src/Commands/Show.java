package Commands;

import CollectionWrappers.CollectionManager;
import Entities.Flat;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 * ������� "show" - ������� ��� �������� ���������
 */
public class Show extends BaseCommand implements CommandWithoutInput{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Show() {
        super("show","������� ��� �������� ���������");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        if (manager.getList().size()==0){
            System.out.println("��������� ������");
        }
        else {
            manager.getList().forEach(System.out::println);
        }
    }
}
