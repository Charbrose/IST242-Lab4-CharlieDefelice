package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException; // Import TimeoutException

public class Recv {
    private final static String QUEUE_NAME = "pizza_queue";

    public static void receivePizzaFromQueue() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection(); // Try to establish connection
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for pizza orders. To exit, press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                // Deserialize the message into a Pizza object
                Pizza receivedPizza = deserializePizza(message);
                System.out.println(" [x] Received pizza: " + receivedPizza);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) { // Catch IOException and TimeoutException
            e.printStackTrace();
        }
    }

    // Deserialize the JSON message into a Pizza object
    private static Pizza deserializePizza(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Pizza.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle deserialization error gracefully
        }
    }
}
