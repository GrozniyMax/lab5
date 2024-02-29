package Commands;

/**
 * Абстрактный класс команды
 * @author Максим Тараненко
 */
public abstract class BaseCommand implements Command{
    //TODO Переписать команды на самостоятельный парсинг аргумента без RegExp


    /**
     * Описание команды
     */
    private final String description;
    /**
     * Название команды
     */
    private final String name;


    protected BaseCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Получить описание команды
     * @return - описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получить название команды
     * @return - название команды
     */
    public String getName() {
        return name;
    }

}
