package MMAD;
import java.util.*;
public class Artist extends Item {
    private String id;
    private String name;

    public Artist(String id, String name){
        super(id, name);
        this.id = id;
        this.name = name;
    }

    public String getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
