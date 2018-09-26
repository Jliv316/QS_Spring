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
public class FoodsController {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    MealRepository mealRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/v1/foods")
    public List<Food> index(){
        return foodRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/v1/foods/{id}")
    public Food show(@PathVariable int id){
        return foodRepository.findOne(id);
    }

    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/v1/foods")
    public Food create(@RequestBody String payload){
        try {
            JSONObject jsonObj = new JSONObject(payload);
            String name = jsonObj.getJSONObject("food").get("name").toString();
            String calories = jsonObj.getJSONObject("food").get("calories").toString();
            Food food = new Food(name, Integer.parseInt(calories));
            foodRepository.saveAndFlush(food);
            System.out.println(food);
            return food;
        } catch (JSONException e) {
            //some exception handler code.
            Food food = new Food("Fried Chicken", 2000);
            return food;
        }

    }

    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/api/v1/foods/{id}")
    public Food update(@PathVariable Integer id, @RequestBody String payload){
        try {
            Food food = foodRepository.findOne(id);
            JSONObject jsonObj = new JSONObject(payload);
            String name = jsonObj.getJSONObject("food").get("name").toString();
            String calories = jsonObj.getJSONObject("food").get("calories").toString();
            food.setName(name);
            food.setCalories(Integer.parseInt(calories));
            return food;
        } catch (JSONException e) {
            //some exception handler code.
            Food food = new Food("Fried Chicken", 2000);
            return food;
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/api/v1/foods/{id}")
    public boolean delete(@PathVariable Integer id) {
        Food food = foodRepository.findOne(id);
        mealRepository.findAll().forEach(meal -> {
            meal.deleteFood(food);
        });
        foodRepository.delete(food);

        return true;
    }
}