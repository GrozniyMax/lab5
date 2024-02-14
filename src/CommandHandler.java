import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Обработчик команд
 */
public class CommandHandler {

    LinkedListManager linkedListManager;
    static Map<Pattern, String> methods;

    //Добавляем все методы в словарь
    static {
        methods = new HashMap<>();

        //Добавляем все методы в словарь
        methods.put(Pattern.compile("^\s*help\s*$"),"help");
        methods.put(Pattern.compile("^\s*info\s*$"),"info");
        methods.put(Pattern.compile("^\s*show\s*$"),"show");
        methods.put(Pattern.compile("^\s*add\s*$"),"add");
        methods.put(Pattern.compile("^\s*update\s+(\\d+)$"),"update");
        methods.put(Pattern.compile("^\s*update\s+(\\d+)$"),"removeById");
        methods.put(Pattern.compile("^\s*clear\s*$"),"clear");
        methods.put(Pattern.compile("^\s*save\s*$"),"save");
        methods.put(Pattern.compile("^\s*update\s+(.+)$"),"executeScript");
        methods.put(Pattern.compile("^\s*exit\s*$"),"exit");
        methods.put(Pattern.compile("^\s*remove_first\s*$"),"removeFirst");
        methods.put(Pattern.compile("^\s*remove_lower\s+(.+)\s*$"),"remove_lower");
        methods.put(Pattern.compile("^\s*remove_all_by_view\s+(.+)\s*$"),"remove_all_by_view");
        methods.put(Pattern.compile("^\s*group_by_creation_date\s*$"),"group_by_creation_date");
        methods.put(Pattern.compile("^\s*count_greater_than_furish\s+(.+)$"),"count_greater_than_furish");
    }

    /**
     * Конструктор
     * @param linkedListManager - менеджер коллекцией
     */
    public CommandHandler(LinkedListManager linkedListManager) {
        this.linkedListManager = linkedListManager;
    }


    /**
     * Обработка команды
     * @param line - строка, которую ввел пользователь
     * @return - true, если нужно завершить программу
     */
    public boolean handle(String line){
        String currentMethodName = null;
        Pattern currentPattern = null;
        for (Pattern command:
             methods.keySet()) {
            //write code that check does the line match the pattern
            if (command.matcher(line).matches()){
                currentPattern = command;
                currentMethodName = methods.get(currentPattern);
                break;
            }
        }
        try {
            currentPattern = Objects.requireNonNull(currentPattern,"Не получилось распознать команду");
            if (currentMethodName.contains("exit")) return true;
            Method currentMethod = LinkedListManager.class.getMethod(Objects.requireNonNull(currentMethodName,
                                                            "Не получилось распознать команду"));
            if (currentMethod.getParameterCount()>0){
                Matcher matcher = currentPattern.matcher(line);
                if (matcher.find()){
                    currentMethod.invoke(this.linkedListManager,
                                        matcher.group(1));
                }
            }
            else {
                currentMethod.invoke(this.linkedListManager);
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e){
            ConsoleManager.getInstance().printError(e);
        }catch (NullPointerException e){

        }
        return false;
    }

}
