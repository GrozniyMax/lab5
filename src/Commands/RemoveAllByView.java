package Commands;

import CollectionWrappers.CollectionManager;
import Entities.Flat;
import Entities.View;

import java.util.regex.Matcher;

/**
 * Команда "remove_all_by_view" удаления всех элементов, значение поля view которых эквивалентно введенному
 */
public class RemoveAllByView extends BaseCommand implements CommandWithoutInput{

    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public RemoveAllByView() {
        super("remove_all_by_view",
                "удаляет все элементы, view оторых совпадает с введенным");
    }


    @Override
    public void execute(CollectionManager manager, String argument) {
        View view = View.valueOf(argument.strip().toUpperCase());

        manager.getList().removeIf((Flat colObj)->colObj.getView().equals(view));
        for (int i = 0; i < manager.getList().size(); i++) {
            manager.getList().get(i).setId(Long.valueOf(i));
        }
    }
}
