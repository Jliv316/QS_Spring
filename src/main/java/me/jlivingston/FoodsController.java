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


    @GetMapping("/api/v1/foods")
    public List<Food> index(){
        return foodRepository.findAll();
    }

    @GetMapping("/api/v1/foods/{id}")
    public Food show(@PathVariable int id){
        return foodRepository.findOne(id);
    }

    @PostMapping("/api/v1/foods")
    @Transactional
    public String create(@RequestBody String payload){
        try {
            JSONObject jsonObj = new JSONObject(payload);
            String name = jsonObj.getJSONObject("food").get("name").toString();
            int calories = (int) jsonObj.getJSONObject("food").get("calories");
            System.out.println(calories);
            Food food = new Food(name, calories);
            System.out.println(food);
            foodRepository.saveAndFlush(food);
            System.out.println(food);
            return food.toString();
        } catch (JSONException e) {
            //some exception handler code.
            return "Oops something happened!";
        }

    }

//    @PutMapping("/api/v1/foods/{id}")
//    public Food update(@PathVariable Long id, @RequestBody Map<String, String> body){
//        Food updatedFood = foodRepository.findOne(id);
//        updatedFood.setName(body.get("name"));
//        updatedFood.setCalories(body.get("calories"));
//        return foodRepository.save(updatedFood);
//    }

    @DeleteMapping("/api/v1/foods/{id}")
    public boolean delete(@PathVariable Integer id) {
        foodRepository.delete(id);
        return true;
    }
}