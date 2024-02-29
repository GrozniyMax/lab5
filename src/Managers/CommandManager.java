package Managers;

import CollectionWrappers.CollectionManager;
import Commands.*;
import Exceptions.EndOfStreamException;
import Exceptions.FunctionFailedException;
import Exceptions.InvalidInputException;
import Input.InputManager;

import java.util.HashMap;

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
            ParametresBundle parametresBundle = null;
            switch (currentCommand.getRequiredParametres()){
                case ALL -> parametresBundle = new ParametresBundle(collectionManager,
                            splitted[1],
                            inputManager);
                case COMMANDS -> parametresBundle = new ParametresBundle(commands.values());
                case NOTHING -> parametresBundle = new ParametresBundle();
                case COLLECTION_ONLY -> parametresBundle = new ParametresBundle(collectionManager);
                case READER -> parametresBundle = new ParametresBundle(collectionManager,inputManager);
                case ARGUMENT -> parametresBundle = new ParametresBundle(collectionManager,splitted[1]);
            }
            currentCommand.execute(parametresBundle);
        } catch (IllegalArgumentException e) {
            throw new FunctionFailedException("Введен некорректный аргумент функции. "+e.getMessage());
        } catch (EndOfStreamException e){
            return true;
        } catch (InvalidInputException e){
            throw new FunctionFailedException("Ошибка ввода"+e.getMessage());
        }catch (IndexOutOfBoundsException e){
            throw  new FunctionFailedException("Ошибка ввода. Не введен аргумент функции");
        }
        //TODO Прописать остальные exceptions

        return false;

    }
}
