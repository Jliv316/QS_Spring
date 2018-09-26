package me.jlivingston;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

@RestController
public class MealsController {
    @Autowired
    MealRepository mealRepository;

    @Autowired
    FoodRepository foodRepository;


    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/meals")
    public List<Meal> index(){
        return mealRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/meals/{id}/foods")
    public Meal show(@PathVariable int id){
        return mealRepository.findOne(id);
    }

    @Transactional
    @PostMapping("/api/v1/meals/{meal_id}/foods/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String create(@PathVariable int meal_id, @PathVariable int id){
            System.out.println(meal_id);
            System.out.println(id);
            Meal meal = mealRepository.findOne(meal_id);
            Food food = foodRepository.findOne(id);
            System.out.println(meal_id);
            System.out.println(meal);
            System.out.println(id);
            System.out.println(food);
            meal.addFood(food);
            mealRepository.save(meal);

            return "Successfully added food to meal";
//
    }



//    @PutMapping("/api/v1/foods/{id}")
//    public Food update(@PathVariable Long id, @RequestBody Map<String, String> body){
//        Food updatedFood = foodRepository.findOne(id);
//        updatedFood.setName(body.get("name"));
//        updatedFood.setCalories(body.get("calories"));
//        return foodRepository.save(updatedFood);
//    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/api/v1/meals/{meal_id}/foods/{id}")
    public boolean delete(@PathVariable Integer meal_id, @PathVariable Integer id) {
        Meal meal = mealRepository.findOne(meal_id);
        Food food = foodRepository.findOne(id);
        meal.deleteFood(food);
        mealRepository.save(meal);
        return true;
    }
}