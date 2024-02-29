package Commands;

import CollectionWrappers.CollectionManager;
import Input.BaseInputManager;

import java.util.regex.Matcher;

/**
 *  оманда "history" - выводит последние 5 команд
 */
public class History extends BaseCommand{
    /**
     * ѕустой конструктор
     * @see BaseCommand
     */
    public History() {
        super("history",
                "выводит последние 5 команд");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        parametresBundle.collectionManager().getHistory().forEach(System.out::println);
    }
}
