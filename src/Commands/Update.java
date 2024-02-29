package Commands;

/**
 * ������� "update". ��������� ������ �������� ��������� �� id
 */
public class Update extends BaseCommand {
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Update() {
        super("update",
                "��������� ������ �������� ��������� �� id");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.ALL;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        try {
            Integer id = Integer.parseInt(parametresBundle.argument());
            parametresBundle.collectionManager().getList().set( id,
                    parametresBundle.reader().readFlat());
        } catch ( NumberFormatException e){
            throw new IllegalArgumentException("����������� ��������� id");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(" ��������� id ������ ��� ���������� ��������� �������");
        }
    }
}
