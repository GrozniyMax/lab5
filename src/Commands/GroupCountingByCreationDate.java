package Commands;

import Entities.Flat;
import Managers.InputManager;
import Managers.LinkedListManager;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.regex.Matcher;

/**
 * Класс команды группировки элементов по дате создания
 */
public class GroupCountingByCreationDate extends Command{
    /**
     * Конструктор класса команды
     * @see Command
     */
    public GroupCountingByCreationDate() {
        super("group_counting_by_creation_date",
                "группирует элементы по дате создания, выводя их количество",
                "^\s*group_counting_by_creation_date\s*$");
    }
    /**
     * Обработчик команды
     * @param collection - менеджер коллекции
     * @param matcher - аргументы команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        HashMap<ZonedDateTime,Integer> dates = new HashMap<>();
        ZonedDateTime currentDate;
        for (Flat flat : collection.getList()) {
            currentDate = flat.getCreationDate();
            if (dates.containsKey(currentDate)){
                dates.put(currentDate,dates.get(currentDate)+1);
            }
            else {
                dates.put(currentDate,1);
            }
        }
        InputManager m = InputManager.getInstance();
        for (ZonedDateTime time: dates.keySet()) {
            m.print(time.toString()+" : "+dates.get(time).toString());
        }
    }
}
