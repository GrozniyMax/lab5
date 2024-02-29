package Commands;

import CollectionWrappers.CollectionManager;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 * ������� "history" - ������� ��������� 5 ������
 */
public class History extends BaseCommand{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public History() {
        super("history",
                "������� ��������� 5 ������");
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
        parametresBundle.collectionManager().getHistory().forEach(System.out::println);
    }
}
