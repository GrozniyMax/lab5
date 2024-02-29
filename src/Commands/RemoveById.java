package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;


/**
 * ������� "remove_by_id". ������� ������� �� ��������� �� ��� id.
 */
public class RemoveById extends BaseCommand {
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public RemoveById() {
        super("remove_by_id","������� ������ �� id");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.ARGUMENT;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {

        try {
            Integer id = Integer.parseInt(parametresBundle.argument().strip());
            parametresBundle.collectionManager().getList().remove(id);
            for (int i = 0; i < parametresBundle.collectionManager().getList().size(); i++) {
                parametresBundle.collectionManager().getList().get(i).setId(Long.valueOf(i));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("id ������ ��� ���������� ��������� �������");
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("����������� ��������� id");
        }
    }
}
