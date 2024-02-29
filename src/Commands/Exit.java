package Commands;

/**
 * ����� ������� ���������� ���������
 */
public class Exit extends BaseCommand {
    /**
     * ����������� ������ �������
     * @see BaseCommand
     */
    public Exit() {
        super("exit",
                "��������� ���������� ���������");
    }



    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.NOTHING;
    }


    @Override
    public void execute(ParametresBundle parametresBundle) {
        //DO NOTHING
    }
}
