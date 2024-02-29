package Commands;

/**
 * Команда "update". Обновляет данные элемента коллекции по id
 */
public class Update extends BaseCommand {
    /**
     * Пустой конструктор
     * @see BaseCommand
     */
    public Update() {
        super("update",
                "обновляет данные элемента коллекции по id");
    }

    @Override
    public RequiredParametres getRequiredParametres() {
        return RequiredParametres.ALL;
    }

    @Override
    public void execute(ParametresBundle parametresBundle) {
        try {
            Integer id = Integer.parseInt(parametresBundle.argument());
            parametresBundle.collectionManager().getList().set( id,
                    parametresBundle.reader().readFlat());
        } catch ( NumberFormatException e){
            throw new IllegalArgumentException("Некорректно введенный id");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(" Указанный id больше чем количество элементов массива");
        }
    }
}
