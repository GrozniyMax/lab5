package Commands;

import Managers.LinkedListManager;

import javax.xml.namespace.QName;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Абстрактный класс команды
 * @author Максим Тараненко
 */
public abstract class Command {

    /**
     * Паттерн для поиска команды
     */
    private final Pattern pattern;
    /**
     * Описание команды
     */
    private final String description;
    /**
     * Название команды
     */
    private final String name;

    /**
     * Конструктор команды
     * @param name - название команды
     * @param description - описание команды
     * @param pattern - паттерн для поиска команды
     */
    protected Command( String name,String description, String pattern) {
        this.name = name;
        this.description = description;
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Получить описание команды
     * @return - описание команды
     */
    public String getDescription() {
        return description;
    }
    /**
     * Получить паттерн для поиска команды
     * @return - паттерн для поиска команды
     */
    public Pattern getPattern() {
        return pattern;
    }
    /**
     * Получить название команды
     * @return - название команды
     */
    public String getName() {
        return name;
    }
    /**
     * Выполнить команду
     * @param collection - менеджер коллекции
     * @param matcher - матчер
     */
    public abstract void execute(LinkedListManager collection, Matcher matcher);
}
