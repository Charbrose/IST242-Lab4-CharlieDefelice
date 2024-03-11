package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

public class Send {
    private final static String QUEUE_NAME = "pizza_queue"; // Rename the queue

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // Create a sample Pizza object
            Pizza myPizza = new Pizza("Medium", "Pepperoni", "Thin Crust");
            String message = serializePizza(myPizza); // Serialize the Pizza object
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent pizza: " + myPizza);
        }
    }

    // Sample Pizza class
    static class Pizza {
        private String size;
        private String toppings;
        private String crustType;

        public Pizza(String size, String toppings, String crustType) {
            this.size = size;
            this.toppings = toppings;
            this.crustType = crustType;
        }

        // Getters and setters (omitted for brevity)
    }

    // Serialize the Pizza object into a JSON message
    private static String serializePizza(Pizza pizza) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(pizza);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ""; // Handle serialization error gracefully
        }
    }
}
