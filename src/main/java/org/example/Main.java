//Project: Pizza Order Processing System
//Purpose Details: This project is for handling pizza orders. It helps serialize and deserialize pizza information using JSON and RabbitMQ.
//Course: IST242
//Author: Charlie Defelice
//Date Developed: 3/9/2024
//Last Date Changed: 3/10/2024
//Rev: 4.2

package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
    public static void main(String[] args)
    {
        //creates a fixed thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //submits tasks for receiving and sending orders
        executor.submit(() -> {
            try
            {
                Recv.main(args);
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try
            {
                Send.main(args);
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        //shuts down the executor once tasks are done
        executor.shutdown();
    }
}
