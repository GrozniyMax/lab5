package Commands;

import Entities.Flat;
import Managers.InputManager;
import Managers.LinkedListManager;

import java.util.regex.Matcher;

/**
 * Команда "add"
 */
public class Add extends Command{
    /**
     * Конструктор команды
     * @see Command
     */
    public Add() {
        super("add",
                "добавляет элемент в коллекцию",
                "^\s*add\s*$");
    }
    /**
     * Выполнение команды
     * @param collection - менеджер коллекции
     * @param matcher - Matcher
     * @throws IllegalArgumentException - если введены неверные аргументы
     */
    @Override
    public void execute(LinkedListManager collection, Matcher matcher) throws IllegalArgumentException{
        Flat object = InputManager.getInstance().readFlat();
        object.setId(Long.valueOf(collection.getList().size()));
        collection.getList().add(object);
    }
}
