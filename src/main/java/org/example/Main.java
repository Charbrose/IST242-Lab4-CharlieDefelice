//Project: Pizza Order Processing System
//Purpose Details: This project is for handling pizza orders. It helps serialize and deserialize pizza information using JSON and RabbitMQ.
//Course: IST242
//Author: Charlie Defelice
//Date Developed: 3/9/2024
//Last Date Changed: 3/10/2024
//Rev: 3.4

package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    public static void main(String[] args) {
        // Deserialize the JSON string back to a Pizza object
        String pizzaJson = "{\"size\":\"Medium\",\"toppings\":\"Pepperoni\",\"crustType\":\"Thin Crust\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Pizza deserializedPizza = objectMapper.readValue(pizzaJson, Pizza.class);
            System.out.println("Deserialized Pizza object: " + deserializedPizza);
            System.out.println("Size: " + deserializedPizza.getSize());
            System.out.println("Toppings: " + deserializedPizza.getToppings());
            System.out.println("Crust Type: " + deserializedPizza.getCrustType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
