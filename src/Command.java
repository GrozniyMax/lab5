import java.util.regex.Pattern;

/**
 * Команды
 */
public enum Command {
    HELP("^\s*help\s*$","help"),
    INFO("^\s*info\s*$","info"),
    HISTORY("^\s*history\s*$","history"),
    SHOW("^\s*show\s*$","show"),
    ADD("^\s*add\s*$","add"),
    UPDATE("^\s*update\s+(\\d+)$","update"),
    REMOVE_BY_ID("^\s*remove_by_id\s+(\\d+)$","removeById"),
    CLEAR("^\s*clear\s*$","clear"),
    SAVE("^\s*save\s*$","save"),
    EXECUTE_SCRIPT("^\s*execute_script\s+(.+)$","executeScript"),
    EXIT("^\s*exit\s*$","exit"),
    REMOVE_FIRST("^\s*remove_first\s*$","removeFirst"),
    REMOVE_LOWER("^\s*remove_lower\s+(.+)\s*$","removeLower"),
    REMOVE_ALL_BY_VIEW("^\s*remove_all_by_view\s+(.+)$" ,"removeAllByView"),
    GROUP_COUNTING_BY_CREATION_DATE("^\s*group_counting_by_creation_date\s*$","groupCountingByCreationDate"),
    COUNT_GREATER_THAN_FURISH("^\s*count_greater_than_furish\s+(.+)$","countGreaterThanFurish");


    private Pattern pattern;
    private String commandName;
    /**
     * Конструктор
     * @param pt - паттерн
     */
    Command(String pt, String commandName) {
        this.pattern = Pattern.compile(pt);
        this.commandName = commandName;
    }
    /**
     * Возвращает паттерн
     * @return паттерн
     */
    public Pattern getPattern() {
        return pattern;
    }
    public String getCommandName(){return commandName;}
}
