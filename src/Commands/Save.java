package Commands;

import CollectionWrappers.CollectionManager;
import Input.BaseInputManager;
import Managers.JsonManager;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
/**
 * Команда "save" - сохраняет коллекцию в файл
 */
public class Save extends BaseCommand implements CommandWithoutInput{
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public Save() {
        super("save",
                "сохраняет коллекцию в файл");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        try {
            JsonManager.dump(manager.getCollection());
            System.out.println("Коллекция сохранена");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
