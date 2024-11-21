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

    public String getname(){
        return this.name;
    }
}


