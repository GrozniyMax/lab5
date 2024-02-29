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
    /**
     * @see Command#getRequiredParametres()
     */
    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }

    /**
     * @see Command#execute(ParametresBundle) ()
     */
    @Override
    public void execute(ParametresBundle parametresBundle) {
        parametresBundle.collectionManager().getList().clear();
    }
}
