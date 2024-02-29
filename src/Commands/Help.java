package Commands;

import Input.BaseInputManager;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * @see Command#getRequiredParametres()
     */
    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COMMANDS;
    }

    /**
     * @see Command#execute(ParametresBundle) ()
     */
    @Override
    public void execute(ParametresBundle parametresBundle) {
        System.out.println("����� �����: ������� ���� Flat � ��������� �������: name, coordinates, area, numberOfRooms, furnish, view, transport, house");
        System.out.println("���� house ������� � ��������� �������: name, year, numberOfFloors, numberOfFloors");
        System.out.println("��� ���� �������� �� ������ � ������");
        System.out.println("��������� �������:");
        parametresBundle.commands().forEach((Command c)->{System.out.println(c.getName()+" : "+c.getDescription());});
    }
}
