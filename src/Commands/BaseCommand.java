package Commands;

/**
 * ����������� ����� �������
 * @author ������ ���������
 */
public abstract class BaseCommand implements Command{
    //TODO ���������� ������� �� ��������������� ������� ��������� ��� RegExp


    /**
     * �������� �������
     */
    private final String description;
    /**
     * �������� �������
     */
    private final String name;


    protected BaseCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * �������� �������� �������
     * @return - �������� �������
     */
    public String getDescription() {
        return description;
    }

    /**
     * �������� �������� �������
     * @return - �������� �������
     */
    public String getName() {
        return name;
    }

}
