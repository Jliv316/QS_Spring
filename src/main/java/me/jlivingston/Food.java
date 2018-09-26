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
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.Serializable;

@Entity
public class Food implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer calories;

    @ManyToMany(mappedBy = "foods")
    private Set<Meal> meals;

    public Food() { }

    public Food(String name, int calories) {
        this.setId(id);
        this.setName(name);
        this.setCalories(calories);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

//
//    @Transactional
//    public void remove(Integer foodId) {
//        Food food = foodRepository.findOne(foodId);
//        food.getMeals().removeAll(food.getMeals());
//
//        // Other business logic
//
//        foodRepository.delete(food);
//    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                '}';
    }
}
