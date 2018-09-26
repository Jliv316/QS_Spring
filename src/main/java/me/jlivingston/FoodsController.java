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

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/foods")
    public List<Food> index(){
        return foodRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/foods/{id}")
    public Food show(@PathVariable int id){
        return foodRepository.findOne(id);
    }

    @PostMapping("/api/v1/foods")
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Food create(@RequestBody String payload){
        try {
            System.out.println(payload);
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

//    @PutMapping("/api/v1/foods/{id}")
//    public Food update(@PathVariable Long id, @RequestBody Map<String, String> body){
//        Food updatedFood = foodRepository.findOne(id);
//        updatedFood.setName(body.get("name"));
//        updatedFood.setCalories(body.get("calories"));
//        return foodRepository.save(updatedFood);
//    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/api/v1/foods/{id}")
    public boolean delete(@PathVariable Integer id) {
        foodRepository.delete(id);
        return true;
    }
}