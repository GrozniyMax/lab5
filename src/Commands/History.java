package Commands;

import CollectionWrappers.CollectionManager;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 *  оманда "history" - выводит последние 5 команд
 */
public class History extends BaseCommand implements CommandWithoutInput{
    /**
     * ѕустой конструктор
     * @see BaseCommand
     */
    public History() {
        super("history",
                "выводит последние 5 команд");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        manager.getHistory().forEach(System.out::println);
    }
}
