package MMAD;

public class Artist {
    private String id;
    private String name;

    public Artist(String id, String name){
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
