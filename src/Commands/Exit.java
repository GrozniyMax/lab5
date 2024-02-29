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


    /**
     * @see Command#getRequiredParametres()
     */
    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.NOTHING;
    }

    /**
     * @see Command#execute(ParametresBundle) ()
     */
    @Override
    public void execute(ParametresBundle parametresBundle) {
        //DO NOTHING
    }
}
