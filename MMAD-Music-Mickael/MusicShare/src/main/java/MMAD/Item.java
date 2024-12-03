package MMAD;
public abstract class Item {
    protected int id;
    protected String sourceID;
    protected String name;

    public Item(String sourceID, String name){
        this.sourceID = sourceID;
        this.id = -1;
        this.name = name;
    }

    public Item(int id, String sourceID, String name){
        this.id = id;
        this.sourceID = sourceID;
        this.name = name;
    }

    public abstract int getID();

    public abstract String getSourceID();

    public abstract String getName();
}


