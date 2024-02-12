import java.util.LinkedList;
import java.util.regex.Matcher;

public class CommandHandler {

    LinkedListManager linkedListManager;

    public CommandHandler(LinkedListManager linkedListManager) {
        this.linkedListManager = linkedListManager;
    }

    /**
     * Обработка команды
     * @param line - строка, которую ввел пользователь
     * @return - true, если нужно завершить программу
     */
    public boolean handle(String line){
        Command current = null;

        for (Command c :
                Command.values()) {
            if (c.getPattern().matcher(line).matches()){
                current = c;
                break;
            }
        }
        String argument = null;
        if ((current== Command.UPDATE)||(current==Command.REMOVE_BY_ID)||(current==Command.EXECUTE_SCRIPT)||(current==Command.REMOVE_ALL_BY_VIEW)||(current==Command.COUNT_GREATER_THAN_FURISH)){

            Matcher matcher = current.getPattern().matcher(line);
            if (matcher.find()){
                argument = matcher.group(1);
            }
            else {
                throw new RuntimeException("Не получилось распарсить аргумент");
            }
        }

        switch (current){
            case EXIT : {
                return true;
            }
            case HELP : {
                linkedListManager.help();
                break;
            }
            case INFO : {
                linkedListManager.info();
                break;
            }
            case ADD : {
                linkedListManager.add();
                break;
            }
            case SHOW : {
                linkedListManager.show();
                break;
            }
            case UPDATE : {
                linkedListManager.update(argument);
                break;
            }
            case REMOVE_BY_ID : {
                linkedListManager.removeById(argument);
                break;
            }
            case CLEAR : {
                linkedListManager.clear();
                break;
            }
            case SAVE : {
                linkedListManager.save();
                break;
            }
            case EXECUTE_SCRIPT : {
                linkedListManager.executeScript(argument);
                break;
            }
            case REMOVE_FIRST : {
                linkedListManager.removeFirst();
                break;
            }
            case REMOVE_LOWER : {
                linkedListManager.removeLower();
                break;
            }
            case REMOVE_ALL_BY_VIEW : {
                linkedListManager.removeAllByView(argument);
                break;
            }
            case GROUP_COUNTING_BY_CREATION_DATE : {
                linkedListManager.groupCountingByCreationDate();
                break;
            }
            case COUNT_GREATER_THAN_FURISH : {linkedListManager.countGreaterThanFurish(argument);break;}
            default : System.out.println("Команда не распознана");
        }
        return false;
    }
}
