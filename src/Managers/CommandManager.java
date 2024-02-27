package Managers;

import CollectionWrappers.CollectionManager;
import Commands.*;
import Commands.CommandsWithInput.Add;
import Commands.CommandsWithInput.CommandWithInput;
import Commands.CommandsWithInput.RemoveLower;
import Commands.CommandsWithInput.Update;
import Exceptions.EndOfStreamException;
import Exceptions.FunctionFailedException;
import Exceptions.InvalidInputException;
import Input.InputManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CommandManager {
    //TODO прописать handle для одной команды и бесконечного количества команд
    //TODO указать InputManager как поле CommandManagera
    CollectionManager collectionManager;
    InputManager inputManager;

    private Map<String,Command> commands = new HashMap<>();

    {
        //Блок инициализации чтобы заполнить список команд
        register(new Add());
        register(new Clear());
        register(new CountGreaterThanFurish());
        register(new ExecuteScript());
        register(new Exit());
        register(new GroupCountingByCreationDate());
        register(new Help());
        register(new History());
        register(new Info());
        register(new RemoveAllByView());
        register(new RemoveById());
        register(new RemoveFirst());
        register(new RemoveLower());
        register(new Save());
        register(new Show());
        register(new Update());
    }

    public void register(Command newCommand){
        commands.put(newCommand.getName(),newCommand);
    }



    public CommandManager(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    public CommandManager(CollectionManager collectionManager, InputManager inputManager, Map<String, Command> commands) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
        this.commands = commands;
    }

    public boolean handle() throws FunctionFailedException {
        try {
            String line = inputManager.readLine();
            String[] splitted = line.strip().split(" +");

            Command currentCommand = null;
            for (String commandName :
                    commands.keySet()) {
                if (commandName.equals(splitted[0])) {
                    currentCommand = commands.get(commandName);
                    break;
                }
            }
            if (currentCommand == null){
                System.err.println("Команда не распознана");
                return false;
            }
            collectionManager.addHistory(currentCommand.getName());
            if (currentCommand.getName().equals("exit")){
                return true;
            }
            String argument = splitted.length<2?"":splitted[1];
            if (currentCommand instanceof Help){
                ((Help) currentCommand).execute(new LinkedList<Command>(commands.values()));
            }else if (currentCommand instanceof CommandWithoutInput){
                ((CommandWithoutInput) currentCommand).execute(collectionManager,argument);
            } else if (currentCommand instanceof CommandWithInput) {
                ((CommandWithInput) currentCommand).execute(collectionManager,argument,inputManager);
            }
        } catch (IllegalArgumentException e) {
            throw new FunctionFailedException("Введен некорректный аргумент функции. "+e.getMessage());
        } catch (EndOfStreamException e){
            return true;
        } catch (InvalidInputException e){
            throw new FunctionFailedException("Ошибка ввода"+e.getMessage());
        }
        //TODO Прописать остальные exceptions

        return false;

    }
}
