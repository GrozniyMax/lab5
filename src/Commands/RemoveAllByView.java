package Commands;

import Entities.Flat;
import Entities.View;
import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "remove_all_by_view" удаления всех элементов, значение поля view которых эквивалентно введенному
 */
public class RemoveAllByView extends Command{

    /**
     * Пустой конструктор
     * @see Command
     */
    public RemoveAllByView() {
        super("remove_all_by_view",
                "удаляет все элементы, view оторых совпадает с введенным",
                "^\s*remove_all_by_view\s+(.+)$");
    }


    /**
     * Реализация команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher сгруппированным по шаблону
     * @throws IllegalArgumentException - если введенное значение не существует
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) throws IllegalArgumentException{
        View view = View.valueOf(matcher.group(1).strip().toUpperCase());

        collection.getList().removeIf((Flat colObj)->colObj.getView().equals(view));
        for (int i = 0; i < collection.getList().size(); i++) {
            collection.getList().get(i).setId(Long.valueOf(i));
        }
    }
}
