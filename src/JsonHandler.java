import Entities.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.util.*;
import java.util.regex.Pattern;

public final class JsonHandler {
    static String filePath;

    private static class ManagerAdapter{
        LinkedList<FlatParams> list;
        public Date creationTime;

        public ManagerAdapter(LinkedListManager manager) {
            this.list = new LinkedList<>();
            this.creationTime = Date.from(manager.creationDate.toInstant());
            for (Flat f :
                    manager.getList()) {
                list.add(new FlatParams(f));
            }
        }
    }


    /**
     * Класс для сохранения коллекции в JSON-файл
     */
    private static class FlatParams{
        public String name; //Поле не может быть null, Строка не может быть пустой
        public Coordinates coordinates; //Поле не может быть null
        public Date creationDate;
        public Integer area; //Максимальное значение поля: 745, Значение поля должно быть больше 0
        public Long numberOfRooms; //Значение поля должно быть больше 0
        public Furnish furnish; //Поле может быть null
        public View view; //Поле не может быть null
        public Transport transport; //Поле не может быть null
        public House house; //Поле не может быть null

        public FlatParams(Flat copyFrom) {
            this.name = copyFrom.getName();
            this.creationDate = Date.from(copyFrom.getCreationDate().toInstant());
            this.coordinates = copyFrom.getCoordinates();
            this.transport=copyFrom.getTransport();
            this.area = copyFrom.getArea();
            this.numberOfRooms = copyFrom.getNumberOfRooms();
            this.furnish = copyFrom.getFurnish();
            this.view = copyFrom.getView();
            this.house = copyFrom.getHouse();
        }
    }
    /**
     * Класс для сохранения менеджера коллекции в JSON-файл
     */


    /**
     * Устанавливает путь к файлу
     * @param filePath1 - путь к файлу
     */
    public static void setFilePath(String filePath1){
        filePath = filePath1;
    }

    /**
     * Сохраняет коллекцию в JSON-файл
     * @param manager - менеджер коллекции
     * @throws FileNotFoundException - если файл не найден
     */
    static void dump(LinkedListManager manager) throws FileNotFoundException {
        ManagerAdapter managerAdapter = new ManagerAdapter(manager);
        OutputStreamWriter writer = null;
        Gson g = new Gson();
        String file = g.toJson(managerAdapter);
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
            writer.write(file);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Программист даун");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Указаный файл не найден");
        } catch (IOException e) {
            throw new RuntimeException("Не получилось сохранить коллекцию в JSON-файл");
        }
        finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Загружает менеджера из JSON-файла
     * @return - менеджер коллекции
     * @throws IllegalArgumentException - если файл не найден
     */
    static LinkedListManager load() throws IllegalArgumentException{

        StringBuilder lines = new StringBuilder();
        ZonedDateTime resultTime;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            for (; ; ) {
                String line = reader.readLine();
                if (line == null)
                    break;
                lines.append(line.replace("\n",""));
            }
        } catch (IOException e) {
            throw new RuntimeException("Не получилось распарсить коллекцию из JSON-файла");
        }



        ManagerAdapter adapter= new Gson().fromJson(lines.toString(),ManagerAdapter.class);
        LinkedList<Flat> list = new LinkedList<>();
        long currentId=0;
        for (int i=0;i<adapter.list.size();i++) {
            try {
                FlatParams transferObject = adapter.list.get(i);
                Flat f = new Flat();
                f.setName(transferObject.name);
                f.setCreationDate(ZonedDateTime.ofInstant(transferObject.creationDate.toInstant(),ZoneId.systemDefault()));
                f.setCoordinates(transferObject.coordinates);
                f.setArea(transferObject.area);
                f.setNumberOfRooms(transferObject.numberOfRooms);
                f.setView(transferObject.view);
                f.setFurnish(transferObject.furnish);
                f.setHouse(transferObject.house);
                f.setTransport(transferObject.transport);
                f.setId(currentId);
                currentId++;
                list.add(f);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("В процессе обработки файла возникла ошибка в объекте "+ i + ":" + e.getMessage());
            }
        }
        return new LinkedListManager(list,ZonedDateTime.ofInstant(adapter.creationTime.toInstant(),ZoneId.systemDefault()));


    }


}
