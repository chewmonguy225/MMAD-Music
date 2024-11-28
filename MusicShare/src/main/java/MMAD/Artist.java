package MMAD;

public class Artist extends Item {

    public Artist(String sourceID, String name){
        super(sourceID, name);
    }
    public Artist(String id, String sourceID, String name){
        super(id, sourceID, name);
    }

    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public String getSourceID(){
        return this.sourceID;
    }
    @Override
    public String getID(){
        return this.id;
    }
}
