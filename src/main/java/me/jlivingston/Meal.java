package me.jlivingston;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.Serializable;

@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "meal_foods", joinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"))
    private Set<Food> foods;

    public Meal(){

    }

    public Meal(Integer id, String name){
        this.setId(id);
        this.name = name;
    }

    public Meal(String name, Set<Food> foods){
        this.name = name;
        this.foods = foods;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // foods
    public Set<Food> getFoods() {
        return foods;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }

    public void addFood(Food food){
        this.foods.add(food);
    }

    public void deleteFood(Food food){
        this.foods.remove(food);
    }

    @Override
    public String toString(){
        try {
            String info = "";
            JSONObject jsonInfo = new JSONObject();
            jsonInfo.put("id", this.id);
            jsonInfo.put("name", this.name);
            JSONArray subArray = new JSONArray();
            this.foods.forEach(sub -> {
                JSONObject subJson = new JSONObject();
                try {
                    subJson.put("name", sub.getName());
                } catch(JSONException e){
                    System.out.println("not a hash");
                }
                subArray.put(subJson);
            });
            jsonInfo.put("foods", subArray);
            info = jsonInfo.toString();
            return info;
        } catch(JSONException e){
            return "not a hash";
        }
    }
}

