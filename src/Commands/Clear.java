package Commands;

import CollectionWrappers.CollectionManager;


/**
 * ������� ������� ���������
 */
public class Clear extends BaseCommand {
    /**
     * ����������� ��� ����������
     * @see BaseCommand
     */
    public Clear() {
        super("clear",
                "�������� ���������");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }


    @Override
    public void execute(ParametresBundle parametresBundle) {
        parametresBundle.collectionManager().getList().clear();
    }
}
