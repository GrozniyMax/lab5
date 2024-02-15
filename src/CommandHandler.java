import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Обработчик команд
 */
public class CommandHandler {

    LinkedListManager linkedListManager;
    String[] history;
    int historyIndex = 0;



    /**
     * Конструктор
     * @param linkedListManager - менеджер коллекцией
     */
    public CommandHandler(LinkedListManager linkedListManager) {
        this.history = new String[5];
        this.linkedListManager = linkedListManager;
    }

    private void getHistory(){
        InputManager m = InputManager.getInstance();
        for (int i = historyIndex; i < historyIndex+5; i++) {
            if (history[i%5]!=null) {
                m.print(history[i % 5]);
            }
        }
    }

    private void addHistory(String command){
        history[historyIndex] = command;
        historyIndex = (historyIndex+1)%5;
    }

    public boolean handle(Pair<Command, Matcher> pair) throws RuntimeException{
        try {
            String methodName = pair.getKey().getCommandName();
            if (methodName.equals("exit")) return true;
            if (methodName.equals("history")) {
                getHistory();
                addHistory(methodName);
                return false;
            }
            addHistory(methodName);
            Method currentMethod = findMethod(methodName);
            if (currentMethod.getParameterCount()>0){
                Matcher matcher = pair.getValue();
                currentMethod.invoke(this.linkedListManager, matcher.group(1));

            }
            else {
                currentMethod.invoke(this.linkedListManager);
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {

            throw new RuntimeException(e);
        } catch (IllegalArgumentException e){
            InputManager.getInstance().printError(e);
        }catch (NullPointerException e){
            if (e.getMessage().equals("EOF")) return true;
        }
        return false;
    }


    private Method findMethod(String methodName) throws NoSuchMethodException {
        for (Method method : LinkedListManager.class.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }

}
