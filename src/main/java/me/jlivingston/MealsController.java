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


    @GetMapping("/api/v1/meals")
    public List<Meal> index(){
        return mealRepository.findAll();
    }

    @GetMapping("/api/v1/meals/{id}")
    public Meal show(@PathVariable int id){
        return mealRepository.findOne(id);
    }



//    @PutMapping("/api/v1/foods/{id}")
//    public Food update(@PathVariable Long id, @RequestBody Map<String, String> body){
//        Food updatedFood = foodRepository.findOne(id);
//        updatedFood.setName(body.get("name"));
//        updatedFood.setCalories(body.get("calories"));
//        return foodRepository.save(updatedFood);
//    }

    @DeleteMapping("/api/v1/meals/{id}")
    public boolean delete(@PathVariable Integer id) {
        mealRepository.delete(id);
        return true;
    }
}