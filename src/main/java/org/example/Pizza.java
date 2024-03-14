package org.example;

import java.util.List;

public class Pizza {
    private String pizzaName;
    private List<String> toppings;

    //constructors
    public Pizza() {
        //default constructor
    }

    public Pizza(String pizzaName, List<String> toppings) {
        this.pizzaName = pizzaName;
        this.toppings = toppings;
    }

    //getter and setter methods
    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    //pizza details
    public void displayPizzaDetails() {
        System.out.println("Pizza: " + pizzaName);
        System.out.println("Toppings: " + toppings);
    }

    //serializes the pizza
    public static String serializeToJson(Pizza pizza) {
        // Implement serialization logic (e.g., using Gson)
        return ""; // Placeholder
    }

    //deserializes the pizza
    public static Pizza deserializeFromJson(String json) {
        //implements deserialization logic using Gson
        return new Pizza(); //placeholder pizza
    }
}
