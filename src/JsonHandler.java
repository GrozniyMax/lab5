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
    }
    /**
     * Класс для сохранения менеджера коллекции в JSON-файл
     */
    private static class ManagerAdapter{
        public Date creationDate;
        public LinkedList<FlatParams> list;
    }

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
        LinkedList<FlatParams> collection = new LinkedList<>();
        FlatParams transferObj;
        try {
            for (Flat object :
                    manager.list) {
                transferObj = new FlatParams();
                for (Field f :
                        object.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (f.getName().equals("creationDate")){
                            ZonedDateTime time = (ZonedDateTime) f.get(object);
                            transferObj.creationDate = Date.from(time.toInstant());
                    }
                    else {
                        FlatParams.class.getField(f.getName()).set(transferObj,f.get(object));
                    }

                }
                collection.add(transferObj);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Разраб даун. Не смог скастить типы");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Разраб даун. Не смог скастить типы");
        }
        ManagerAdapter saveObj= new ManagerAdapter();
        saveObj.creationDate = Date.from(manager.creationDate.toInstant());


        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
            writer.write(manager.creationDate.toString()+"\n");
            writer.write(new Gson().toJson(collection));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Программист даун");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Указаный файл не найден");
        } catch (IOException e) {
            throw new RuntimeException("Не получилось сохранить коллекцию в JSON-файл");
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


//        final Type listType = new TypeToken<LinkedList<FlatParams>>() {}.getType();
        ManagerAdapter adapter= new Gson().fromJson(lines.toString(),ManagerAdapter.class);
        LinkedList<FlatParams> parsed = adapter.list;
        LinkedList<Flat> resultList = new LinkedList<>();
        Flat finalObject;
        try {
            for (FlatParams transferringObject :
                    parsed) {
                finalObject = new Flat();
                for (Field f :
                        transferringObject.getClass().getDeclaredFields()) {

                    Method setter = Flat.class.getMethod("set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1));

                    if (f.getName().equals("creationDate")) {
                        Date value = Objects.requireNonNull((Date) f.get(transferringObject));
                        finalObject.setCreationDate(ZonedDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault()));
                    } else {

                        setter.invoke(finalObject, f.get(transferringObject));
                    }

                }
                resultList.add(finalObject);
            }
            LinkedListManager result = new LinkedListManager(resultList,ZonedDateTime.ofInstant(adapter.creationDate.toInstant(),ZoneId.systemDefault()));
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Разраб даун. Не смог скастить типы");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }


}
