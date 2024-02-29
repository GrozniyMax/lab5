package Commands;

import Commands.BaseCommand;
import Entities.Flat;
import Input.InputManager;

/**
 * ������� "remove_lover". ������� ��� �������� ������ �����������
 */
public class RemoveLower extends BaseCommand {
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public RemoveLower() {
        super("remove_lover",
                "������� ��� �������� ������ �����������");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.ALL;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        Flat compareObject = parametresBundle.reader().readFlat();
        parametresBundle.collectionManager().getList().removeIf((Flat f)->{
            return f.compareTo(compareObject) == -1;
        });

        for (int i = 0; i < parametresBundle.collectionManager().getList().size(); i++) {
            parametresBundle.collectionManager().getList().get(i).setId((long) i);
        }
    }
}
