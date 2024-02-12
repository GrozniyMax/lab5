package Entities;



import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Класс для хранения объекта типа Flat
 */
public class Flat implements Comparable<Flat>{
    //TODO прописать синхронизацию id
    static long lastId=0;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer area; //Максимальное значение поля: 745, Значение поля должно быть больше 0
    private Long numberOfRooms; //Значение поля должно быть больше 0
    private Furnish furnish; //Поле может быть null
    private View view; //Поле не может быть null
    private Transport transport; //Поле не может быть null
    private House house; //Поле не может быть null
    {
        this.id = lastId++;
        this.creationDate =  ZonedDateTime.now();
    }

    public Flat() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) throws IllegalArgumentException {
        if ((name==null)||name.equals("")) throw new IllegalArgumentException("Имя не должно быть пустым");
        this.name = name;
    }

    public void setCreationDate(ZonedDateTime creationDate)throws IllegalArgumentException {
        if (creationDate==null) throw new IllegalArgumentException("Дата не должна быть пустая");
        this.creationDate = creationDate;
    }

    public void setCoordinates(Coordinates coordinates) throws IllegalArgumentException {
        if (coordinates==null) throw new IllegalArgumentException("Координаты не должны быть пустыми");
        this.coordinates = coordinates;
    }

    public void setArea(Integer area) throws IllegalArgumentException {
        if ((area==null)||(area<0)||(area>745)) throw new IllegalArgumentException("Некорректное значение Flat.area");
        this.area = area;
    }

    public void setNumberOfRooms(Long numberOfRooms) throws IllegalArgumentException {
        if ((numberOfRooms==null)||(numberOfRooms<0)) throw new IllegalArgumentException("Некорректное значение Flat.numberOfRooms");
        this.numberOfRooms = numberOfRooms;
    }

    public void setFurnish(Furnish furnish) throws IllegalArgumentException {
        if (furnish==null) throw new IllegalArgumentException("Поле не должно быть пустым");
        this.furnish = furnish;
    }

    public void setView(View view) throws IllegalArgumentException {
        if (view==null) throw new IllegalArgumentException("Поле не должно быть пустым");
        this.view = view;
    }

    public void setTransport(Transport transport) throws IllegalArgumentException {
        if (transport==null) throw new IllegalArgumentException("Поле не должно быть пустым");
        this.transport = transport;
    }

    public void setHouse(House house) throws IllegalArgumentException {
        if (house==null) throw new IllegalArgumentException("Поле не должно быть пустым");
        this.house = house;
    }

    @Override
    public int compareTo(Flat o) {
        if (o==null) return 1;
        return area.compareTo(o.area);
    }

    public Long getId() {
        return id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public View getView() {
        return view;
    }


    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate.toString() +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", furnish=" + furnish.toString() +
                ", view=" + view.toString() +
                ", transport=" + transport.toString() +
                ", house=" + house.toString() +
                '}';
    }
}