package Commands;

import CollectionWrappers.CollectionManager;
import Entities.Flat;
import Entities.View;

import java.util.regex.Matcher;

/**
 * ������� "remove_all_by_view" �������� ���� ���������, �������� ���� view ������� ������������ ����������
 */
public class RemoveAllByView extends BaseCommand{

    /**
     * ������ �����������
     * @see BaseCommand
     */
    public RemoveAllByView() {
        super("remove_all_by_view",
                "������� ��� ��������, view ������ ��������� � ���������");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.ARGUMENT;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        View view = View.valueOf(parametresBundle.argument().strip().toUpperCase());

        parametresBundle.collectionManager().getList().removeIf((Flat colObj)->colObj.getView().equals(view));
        for (int i = 0; i < parametresBundle.collectionManager().getList().size(); i++) {
            parametresBundle.collectionManager().getList().get(i).setId(Long.valueOf(i));
        }
    }
}
