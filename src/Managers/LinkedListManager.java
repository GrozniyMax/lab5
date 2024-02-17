package Managers;

import Commands.*;
import Entities.Flat;
import Utils.Pair;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Класс для управления коллекцией
 */
public class LinkedListManager {

    /**
     * Коллекция
     */
    private LinkedList<Flat> list;
    /**
     * Дата создания менеджера/коллекции
     */
    final public ZonedDateTime creationDate;
    /**
     * История команд
     */
    private String[] history = new String[5];
    /**
     * Индекс куда записывать последнюю команду
     */
    private int historyIndex = 0;

    /**
     * Список команд
     */
    private List<Command> commands = new ArrayList<>();

    {
        //Блок инициализации чтобы заполнить список команд
        commands.add(new Add());
        commands.add(new Clear());
        commands.add(new CountGreaterThanFurish());
        commands.add(new ExecuteScript());
        commands.add(new Exit());
        commands.add(new GroupCountingByCreationDate());
        commands.add(new Help());
        commands.add(new History());
        commands.add(new Info());
        commands.add(new RemoveAllByView());
        commands.add(new RemoveById());
        commands.add(new RemoveFirst());
        commands.add(new RemoveLower());
        commands.add(new Save());
        commands.add(new Show());
        commands.add(new Update());
    }

    /**
     * Конструктор
     * @param list - коллекция
     * @param creationDate - дата создания
     */
    public LinkedListManager(LinkedList<Flat> list, ZonedDateTime creationDate) {
        this.list = list;
        this.creationDate = creationDate;
    }

    /**
     * Возращает индекс в массиве куда записать текущую команду
     * @return индекс
     */
    public int getHistoryIndex() {
        return historyIndex;
    }

    /**
     * Возвращает коллекцию
     * @return коллекция
     */
    public LinkedList<Flat> getList() {
        return list;
    }
    /**
     * Возвращает историю команд
     * @return история команд
     */
    public String[] getHistory() {
        return history;
    }

    /**
     * Возвращает список команд
     * @return - список команд
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Добавляет команду в историю
     * @param command - команда
     */
    private void addHistory(String command){
        history[historyIndex] = command;
        historyIndex = (historyIndex+1)%5;
    }

    /**
     * Обрабатывает команду
     * @param pair - пара команда-матчер
     * @return - true если команда exit
     * @throws RuntimeException
     */
    public boolean handle(Pair<Command, Matcher> pair) throws RuntimeException{
        boolean result = false;
        try {
            Command executingCommand = pair.getKey();
            addHistory(executingCommand.getName());
            if (executingCommand.getName().equals("exit")) result= true;
            executingCommand.execute(this, pair.getValue());

        } catch (IllegalArgumentException e){
            InputManager.getInstance().printError(e);
        }catch (NullPointerException e){
            if (e.getMessage().equals("EOF")) return true;
        }
        return result;
    }

}


