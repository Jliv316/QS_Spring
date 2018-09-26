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
        Meal breakfast = new Meal("Breakfast");
        Meal snack = new Meal("Snack");
        Meal lunch = new Meal("Lunch");
        Meal dinner = new Meal("Dinner");


        Food cheese = new Food("Cheese", 12);
        Food pizza = new Food("Pizza", 200);
        Food submarine = new Food("subs", 300);





		/*subjectRepository.save(math);
		subjectRepository.save(computer);*/

        Set<Food> foods = new HashSet<Food>();
        foods.add(cheese);
        foods.add(pizza);
        foods.add(submarine);

        breakfast.setFoods(foods);
        lunch.setFoods(foods);

        mealRepository.save(breakfast);
        mealRepository.save(lunch);


        Set<Meal> meals = new HashSet<Meal>();
        meals.add(snack);
        meals.add(dinner);

        cheese.setMeals(meals);
        submarine.setMeals(meals);

        foodRepository.save(cheese);
        foodRepository.save(submarine);

        List<Meal> mealLst = mealRepository.findAll();
        List<Food> foodLst = foodRepository.findAll();

        System.out.println(mealLst.size());
        System.out.println(foodLst.size());


        System.out.println("===================Meals info:==================");
        mealLst.forEach(meal->System.out.println(meal.toString()));

        System.out.println("===================Foods info:==================");
        foodLst.forEach(food->System.out.println(food.toString()));
    }
}
