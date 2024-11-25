package MMAD;
public abstract class Item {
    protected String id;
    protected String sourceID;
    protected String name;

    public Item(String sourceID, String name){
        this.sourceID = sourceID;
        this.id = null;
        this.name = name;
    }

    public Item(String id, String sourceID, String name){
        this.id = id;
        this.sourceID = sourceID;
        this.name = name;
    }

    public abstract String getID();

    public abstract String getSourceID();

    public abstract String getname();
}


