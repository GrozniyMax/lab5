package Commands;

import CollectionWrappers.CollectionManager;

import Managers.JsonManager;

import java.io.FileNotFoundException;

/**
 * ������� "save" - ��������� ��������� � ����
 */
public class Save extends BaseCommand{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Save() {
        super("save",
                "��������� ��������� � ����");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        try {
            JsonManager.dump(parametresBundle.collectionManager().getCollection());
            System.out.println("��������� ���������");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
