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
    
    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }

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
