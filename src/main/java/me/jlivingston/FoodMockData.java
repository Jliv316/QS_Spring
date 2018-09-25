//package me.jlivingston;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FoodMockData {
//
//    //list of blog posts
//    private List<Food> foods;
//
//    private static FoodMockData instance = null;
//    public static FoodMockData getInstance(){
//        if(instance == null){
//            instance = new FoodMockData();
//        }
//        return instance;
//    }
//
//    public FoodMockData(){
//        foods = new ArrayList<Food>();
//        foods.add(new Food(1, "Chicken",
//                200));
//        foods.add(new Food(2, "Maggots",
//                200));
//        foods.add(new Food(3, "Larva",
//                200));
//        foods.add(new Food(4, "Normal Stuff",
//                200));
//        foods.add(new Food(5, "Salad",
//                200));
//        foods.add(new Food(6, "Ice Cream",
//                500));
//    }
//
//    // return all foods
//    public List<Food> getFoods() {
//        return foods;
//    }
//
//    // return food by name
//    public Food getFoodById(int id) {
//        for(Food food: foods) {
//            if(food.getId() == id) {
//                return food;
//            }
//        }
//        return null;
//    }
//
//    // create food
//    public Food createFood(String name, int calories) {
//        int id = foods.size() + 1;
//        Food newFood = new Food(id, name, calories);
//        foods.add(newFood);
//        return newFood;
//    }
//
//    // update food
//    public Food updateFood(int id, String name, int calories) {
//        for(Food food: foods) {
//            if(food.getId() == id) {
//                int foodIndex = foods.indexOf(food);
//                food.setName(name);
//                food.setCalories(calories);
//                foods.set(foodIndex, food);
//                return food;
//            }
//        }
//        return null;
//    }
//
//    // delete blog by id
//    public boolean delete(int id) {
//        int foodIndex = -1;
//        for(Food food: foods) {
//            if (food.getId() == id) {
//                foodIndex = foods.indexOf(food);
//                continue;
//            }
//        }
//        if(foodIndex > -1) {
//            foods.remove(foodIndex);
//        }
//        return true;
//    }
//}
