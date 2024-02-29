package Commands;

import CollectionWrappers.CollectionManager;


/**
 * Команда очистки коллекции
 */
public class Clear extends BaseCommand {
    /**
     * Конструктор без параметров
     * @see BaseCommand
     */
    public Clear() {
        super("clear",
                "очишщает коллекцию");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.COLLECTION_ONLY;
    }


    @Override
    public void execute(ParametresBundle parametresBundle) {
        parametresBundle.collectionManager().getList().clear();
    }
}
