package Commands;

import CollectionWrappers.CollectionManager;
import Entities.Flat;
import Entities.Furnish;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 * ������� "count_greater_than_furish"
 */
public class CountGreaterThanFurish extends BaseCommand implements CommandWithoutInput{
    /**
     * ����������� �������
     * @see BaseCommand
     */
    public CountGreaterThanFurish() {
        super("count_greater_than_furish",
                "������� ���������� ���������, ���� Furish ������� ������ ����������");
    }

    @Override
    public void execute(CollectionManager manager, String argument) {
        Furnish furish = Furnish.valueOf(argument.toUpperCase());
        long counter=0;
        for (Flat f : manager.getList() ) {
            if (f.getFurnish().compareTo(furish)>0){
                counter++;
            }
        }
        System.out.println("���������� �������� � ����� ����� Furish " + counter);
    }
}
