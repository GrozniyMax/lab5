package Commands;

public interface Command {

    /**
     * @return ��� ���������
     */
    public String getName();

    /**
     * @return �������� �������
     */
    public String getDescription();

    /**
     * @return ����������� ������� ���������
     */
    public RequiredParametres getRequiredParametres();

    /**
     * ���������� �������
     * @param parametresBundle ����� ����������
     */
    public void execute(ParametresBundle parametresBundle);

}
