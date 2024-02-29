package Commands;

/**
 * ������� ����� �������
 * @author ������ ���������
 */
public abstract class BaseCommand implements Command{



    /**
     * �������� �������
     */
    private final String description;
    /**
     * �������� �������
     */
    private final String name;


    /**
     * @param name
     * @param description
     */
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
