package Commands;

import CollectionWrappers.CollectionManager;

import java.util.regex.Matcher;


/**
 * ������� "remove_by_id". ������� ������� �� ��������� �� ��� id.
 */
public class RemoveById extends BaseCommand implements CommandWithoutInput{
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public RemoveById() {
        super("remove_by_id","������� ������ �� id");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        try {
            Integer id = Integer.parseInt(argument.strip());
            manager.getList().remove(id);
            for (int i = 0; i < manager.getList().size(); i++) {
                manager.getList().get(i).setId(Long.valueOf(i));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("id ������ ��� ���������� ��������� �������");
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("����������� ��������� id");
        }
    }
}
