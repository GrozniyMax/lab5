package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;

/**
 * ������� "remove_first". ������� ������ �������.
 */
public class RemoveFirst extends BaseCommand{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public RemoveFirst() {
        super("remove_first",
                "������� ������ �������");
    }


    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        parametresBundle.collectionManager().getList().remove(0);
        for (int i = 0; i < parametresBundle.collectionManager().getList().size(); i++) {
            parametresBundle.collectionManager().getList().get(i).setId((long) i);
        }
    }
}
