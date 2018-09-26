package me.jlivingston;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import me.jlivingston.Food;
import me.jlivingston.Meal;
import me.jlivingston.FoodRepository;
import me.jlivingston.MealRepository;

@SpringBootApplication
public class MainApplicationClass implements CommandLineRunner {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    MealRepository mealRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }

    @Transactional
    @Override
    public void run(String... arg0) throws Exception {
        Meal breakfast = new Meal(1, "Breakfast");
        Meal snack = new Meal(2,"Snack");
        Meal lunch = new Meal(3,"Lunch");
        Meal dinner = new Meal(4, "Dinner");

        Set<Food> foods = new HashSet<Food>();

        breakfast.setFoods(foods);
        lunch.setFoods(foods);
        snack.setFoods(foods);
        dinner.setFoods(foods);

        mealRepository.save(breakfast);
        mealRepository.save(snack);
        mealRepository.save(lunch);
        mealRepository.save(dinner);
    }
}
