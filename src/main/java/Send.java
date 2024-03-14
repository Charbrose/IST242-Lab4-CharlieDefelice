package org.example;

import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.List;

public class Send
{
    //name of the RabbitMQ queue
    private final static String QUEUE_NAME = "pizza queue";

    public static void main(String[] argv) throws Exception
    {
        //creates connection and sets RabbitMQ host
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel())
        {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //creates the Pizza object
            Pizza pizzaToSend = createPizza();

            //serializes the Pizza object into a JSON message
            String jsonMessage = serializePizza(pizzaToSend);

            //transfers the JSON message to RabbitMQ queue
            channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes());
            System.out.println("Sent Pizza Order: " + pizzaToSend.getPizzaName());
        }
    }

    //creates the Pizza object
    private static Pizza createPizza()
    {
        Pizza pizza = new Pizza();
        pizza.setPizzaName("Pepperoni");
        pizza.setToppings(List.of("Cheese", "Pepperoni"));
        return pizza;
    }

    //serializes the Pizza object into a JSON message
    private static String serializePizza(Pizza pizza)
    {
        Gson gson = new Gson();
        return Pizza.serializeToJson(pizza);
    }
}