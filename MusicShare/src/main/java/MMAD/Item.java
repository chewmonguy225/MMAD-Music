package MMAD;
public abstract class Item {
    private String id;
    private String name;

    public Item(String id, String name){
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


