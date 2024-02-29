package Commands;

import CollectionWrappers.CollectionManager;


/**
 * ������� "show" - ������� ��� �������� ���������
 */
public class Show extends BaseCommand{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Show() {
        super("show","������� ��� �������� ���������");
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
        if (parametresBundle.collectionManager().getList().size()==0){
            System.out.println("��������� ������");
        }
        else {
            parametresBundle.collectionManager().getList().forEach(System.out::println);
        }
    }
}
