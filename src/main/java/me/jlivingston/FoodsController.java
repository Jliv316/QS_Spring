package me.jlivingston;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Food show(@PathVariable Long id){
        Long foodId = id;
        return foodRepository.findOne(foodId);
    }

    @PostMapping("/api/v1/foods")
    public String create(@RequestBody String payload){
//        System.out.println(payload);
//        JSONObject obj = new JSONObject(payload);
//        System.out.println(obj);
        try {
            JSONObject jsonObj = new JSONObject(payload);
            JSONObject food = new JSONObject(jsonObj.get("food"));
            System.out.println(jsonObj);
            System.out.println(food);
            System.out.println(food.getClass());

//            Food newFood = new Food(food.get("name").toString(), 12);
//            newFood.setCalories(parseInt(food.get("calories").toString()));
            return jsonObj.toString();
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
    public boolean delete(@PathVariable Long id) {
        foodRepository.delete(id);
        return true;
    }
}