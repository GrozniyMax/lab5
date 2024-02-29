package Commands;

import Commands.BaseCommand;
import Entities.Flat;
import Input.InputManager;

/**
 * ������� "add"
 */
public class Add extends BaseCommand {
    /**
     * ����������� �������
     * @see BaseCommand
     */
    public Add() {
        super("add",
                "��������� ������� � ���������");
    }


    /**
     * @see Command#getRequiredParametres()
     */
    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.READER;
    }

    /**
     * @see Command#execute(ParametresBundle) ()
     */
    public void execute(ParametresBundle parametresBundle) throws IllegalArgumentException{
        Flat object = parametresBundle.reader().readFlat();
        object.setId((long) parametresBundle.collectionManager().getList().size());
        parametresBundle.collectionManager().getList().add(object);
    }

}
