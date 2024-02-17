package Commands;

import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Класс команды завершения программы
 */
public class Exit extends Command{
    /**
     * Конструктор класса команды
     * @see Command
     */
    public Exit() {
        super("exit",
                "завершает выполнение программы",
                "^\s*exit\s*$");
    }
    /**
     * Обработчик команды
     * @param collection - менеджер коллекции
     * @param matcher - аргументы команды
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) {
        //do nothing;
        //нет необходимости в обработке, так как команда завершает выполнение программы
    }
}
