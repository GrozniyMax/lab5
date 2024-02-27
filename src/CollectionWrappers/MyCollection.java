package CollectionWrappers;

import Entities.Flat;

import java.time.ZonedDateTime;
import java.util.LinkedList;

public class MyCollection {
    private LinkedList<Flat> list;
    public final ZonedDateTime creationDate;

    public MyCollection(LinkedList<Flat> list, ZonedDateTime creationDate) {
        this.list = list;
        this.creationDate = creationDate;
    }

    public LinkedList<Flat> getList() {
        return list;
    }

}
