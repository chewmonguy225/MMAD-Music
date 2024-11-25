package MMAD;

public class Artist extends Item {

    public Artist(String sourceID, String name){
        super(sourceID, name);
    }
    public Artist(String id, String sourceID, String name){
        super(id, sourceID, name);
    }

    public String getname(){
        return this.name;
    }
    public String getSourceID(){
        return this.sourceID;
    }
    public String getID(){
        return this.id;
    }
}
