package Commands;

import Input.BaseInputManager;

import java.util.LinkedList;

/**
 * ������� "help" - ������� ������ �� ��������� ��������
 */
public class Help extends BaseCommand {
    /**
     * ������ �����������
     * @see BaseCommand
     */
    public Help() {
        super("help","������� ������ �� ��������� ��������");
    }


    public void execute(LinkedList<Command> commands) {
        System.out.println("����� �����: ������� ���� Flat � ��������� �������: name, coordinates, area, numberOfRooms, furnish, view, transport, house");
        System.out.println("���� house ������� � ��������� �������: name, year, numberOfFloors, numberOfFloors");
        System.out.println("��� ���� �������� �� ������ � ������");
        System.out.println("��������� �������:");
        commands.forEach((Command c)->{System.out.println(c.getName()+" : "+c.getDescription());});
    }
}
