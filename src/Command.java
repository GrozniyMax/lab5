import java.util.regex.Pattern;

/**
 * Команды
 */
public enum Command {
    HELP("^\s*help\s*$"),
    INFO("^\s*info\s*$"),
    SHOW("^\s*show\s*$"),
    ADD("^\s*add\s*$"),
    UPDATE("^\s*update\s+(\\d+)$"),
    REMOVE_BY_ID("^\s*update\s+(\\d+)$"),
    CLEAR("^\s*clear\s*$"),
    SAVE("^\s*save\s*$"),
    EXECUTE_SCRIPT("^\s*update\s+(.+)$"),
    EXIT("^\s*exit\s*$"),
    REMOVE_FIRST("^\s*remove_firts\s*$"),
    REMOVE_LOWER("^\s*remove_firts\s*$"),
    REMOVE_ALL_BY_VIEW("^\s*remove_all_by_view\s+(.+)$"),
    GROUP_COUNTING_BY_CREATION_DATE("^\s*group_by_creation_date\s*$"),
    COUNT_GREATER_THAN_FURISH("^\s*count_greater_than_furish\s+(.+)$");


    private Pattern pattern;
    /**
     * Конструктор
     * @param pt - паттерн
     */
    Command(String pt) {
        this.pattern = Pattern.compile(pt);
    }
    /**
     * Возвращает паттерн
     * @return паттерн
     */
    public Pattern getPattern() {
        return pattern;
    }
}
