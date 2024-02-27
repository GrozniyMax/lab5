package Commands;

/**
 * Команда "info" - выводит информацию о коллекции
 */
public class Info extends BaseCommand implements CommandWithoutInput{
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public Info() {
        super("info","выводит информацию о коллекции");
    }


    @Override
    public void execute(CollectionWrappers.CollectionManager manager, String argument) {
        System.out.println("ДАТА СОЗДАНИЯ: "+ manager.getCollection().creationDate.toString());
        System.out.println("ТИП КОЛЛЕКЦИИ: "+manager.getList().getClass().getSimpleName());
        System.out.println("КОЛИЧЕСТВО ЭЛЕМЕНТОВ: "+ manager.getList().size());
    }
}
