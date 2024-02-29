package Commands;

/**
 * ������� "info" - ������� ���������� � ���������
 */
public class Info extends BaseCommand {
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Info() {
        super("info","������� ���������� � ���������");
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
        System.out.println("���� ��������: "+ parametresBundle.collectionManager().getCollection().creationDate.toString());
        System.out.println("��� ���������: "+parametresBundle.collectionManager().getList().getClass().getSimpleName());
        System.out.println("���������� ���������: "+ parametresBundle.collectionManager().getList().size());
    }
}
