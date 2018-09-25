package me.jlivingston;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "foods")
public class Food implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int calories;

    public Food() { }

    public Food(Long id, String name, int calories) {
        this.setId(id);
        this.setName(name);
        this.setCalories(calories);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                '}';
    }
}
