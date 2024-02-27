package Commands;

/**
 * ������� "info" - ������� ���������� � ���������
 */
public class Info extends BaseCommand implements CommandWithoutInput{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Info() {
        super("info","������� ���������� � ���������");
    }


    @Override
    public void execute(CollectionWrappers.CollectionManager manager, String argument) {
        System.out.println("���� ��������: "+ manager.getCollection().creationDate.toString());
        System.out.println("��� ���������: "+manager.getList().getClass().getSimpleName());
        System.out.println("���������� ���������: "+ manager.getList().size());
    }
}
