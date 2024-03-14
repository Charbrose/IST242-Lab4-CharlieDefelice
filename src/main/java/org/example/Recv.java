package org.example;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Channel;

public class Recv
{

    private final static String QUEUE_NAME = "pizza queue";

    public static void main(String[] argv) throws Exception
    {
        //sets up RabbitMQ connection
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {

            //declares queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Waiting for Orders. To End Task Press CTRL+C");

            //sets up callback for messages
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                byte[] messageBytes = delivery.getBody();
                String message = new String(messageBytes, "UTF-8");

                //deserializes received JSON message into the Pizza object
                Pizza receivedPizza = deserializePizza(message);

                //prints the received Pizza object details
                System.out.println("Received Pizza Order: ");
                receivedPizza.displayPizzaDetails();
            };
            //starts getting messages from queue
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        }
    }

    //deserializes the JSON message into the Pizza object
    private static Pizza deserializePizza(String jsonMessage)
    {
        return Pizza.deserializeFromJson(jsonMessage);
    }
}