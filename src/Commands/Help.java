package Commands;

import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Команда "help" - выводит правку по доступным командам
 */
public class Help extends Command{
    /**
     * Пустой конструктор
     * @see Command
     */
    public Help() {
        super("help","Выводит правку по доступным командам","^\s*help\s*$");
    }

    /**
     * Реализация команды
     * @param manager - менеджер коллекции
     * @param matcher - Matcher, с помощью которого можно получить параметры команды
     */
    @Override
    public void execute(LinkedListManager manager, Matcher matcher) {
        InputManager m = InputManager.getInstance();
        m.print("ОЧЕНЬ ВАЖНО: Вводить поля Flat в следующем порядке: name, coordinates, area, numberOfRooms, furnish, view, transport, house");
        m.print("Поля house вводить в следующем порядке: name, year, numberOfFloors, numberOfFloors");
        m.print("Все поля вводятся по одному в строку");
        m.print("Доступные команды:");
        manager.getCommands().forEach((Command c)->{m.print(c.getName()+" : "+c.getDescription());});
    }
}
