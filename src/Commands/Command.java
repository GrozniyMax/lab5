package Commands;

public interface Command {
    public String getName();
    public String getDescription();

    public RequiredParametres getRequiredParametres();

    public void execute(ParametresBundle parametresBundle);

}
