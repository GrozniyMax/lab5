package Commands;

import CollectionWrappers.CollectionManager;
import Entities.Flat;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 * Команда "show" - выводит все элементы коллекции
 */
public class Show extends BaseCommand implements CommandWithoutInput{
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public Show() {
        super("show","выводит все элементы коллекции");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        if (manager.getList().size()==0){
            System.out.println("Коллекция пустая");
        }
        else {
            manager.getList().forEach(System.out::println);
        }
    }
}
