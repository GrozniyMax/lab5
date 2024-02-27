package Commands;

import CollectionWrappers.CollectionManager;
import Entities.Flat;
import Entities.Furnish;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 * Команда "count_greater_than_furish"
 */
public class CountGreaterThanFurish extends BaseCommand implements CommandWithoutInput{
    /**
     * Конструктор команды
     * @see BaseCommand
     */
    public CountGreaterThanFurish() {
        super("count_greater_than_furish",
                "считает количество элементов, поле Furish которых больше введенного");
    }

    @Override
    public void execute(CollectionManager manager, String argument) {
        Furnish furish = Furnish.valueOf(argument.toUpperCase());
        long counter=0;
        for (Flat f : manager.getList() ) {
            if (f.getFurnish().compareTo(furish)>0){
                counter++;
            }
        }
        System.out.println("Количество объектов с таким полем Furish " + counter);
    }
}
