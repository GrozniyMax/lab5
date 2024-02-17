package Commands;

import Entities.Flat;
import Entities.Furnish;
import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "count_greater_than_furish"
 */
public class CountGreaterThanFurish extends Command{
    /**
     * Конструктор команды
     * @see Command
     */
    public CountGreaterThanFurish() {
        super("count_greater_than_furish",
                "считает количество элементов, поле Furish которых больше введенного",
                "^\s*count_greater_than_furish\s+(.+)$");
    }
    /**
     * Выполнение команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher
     * @throws IllegalArgumentException - если введен неверный аргумент
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) throws IllegalArgumentException{
        Furnish furish = Furnish.valueOf(matcher.group(1).strip().toUpperCase());

        long counter=0;

        for (Flat f : collection.getList() ) {
            if (f.getFurnish().compareTo(furish)>0){
                counter++;
            }
        }
        InputManager.getInstance().print("Количество объектов с таким полем Furish " + counter);
    }
}
