package hello;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Random;

public class User extends ResourceSupport {

    private final long id;
    private final ArrayList<Float> results;

    public User(long id){
        this.id = id;
        this.results = new ArrayList<>();

        //Generate random numbers to add as results
        for(int i = 0; i<10; i++){
            results.add(new Random().nextFloat());
        }
    }

    public long getUserId(){
        return this.id;
    }

    public ArrayList<Float> getResults(){
        return this.results;
    }

    public void addResult(Float input){
        this.results.add(input);
    }
}
