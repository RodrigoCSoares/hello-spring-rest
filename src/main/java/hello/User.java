package hello;

import java.util.ArrayList;

public class User {

    private final long id;
    private final ArrayList<Float> results;

    public User(long id){
        this.id = id;
        this.results = new ArrayList<>();
    }

    public long getId(){
        return this.id;
    }

    public ArrayList<Float> getResults(){
        return this.results;
    }

    public void addResult(Float input){
        this.results.add(input);
    }
}
