package org.example;

public class Pizza
{
    private String size;
    private String toppings;
    private String crustType;

    //default constructor
    public Pizza()
    {

    }

    public Pizza(String size, String toppings, String crustType)
    {
        this.size = size;
        this.toppings = toppings;
        this.crustType = crustType;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getToppings()
    {
        return toppings;
    }

    public void setToppings(String toppings)
    {
        this.toppings = toppings;
    }

    public String getCrustType()
    {
        return crustType;
    }

    public void setCrustType(String crustType)
    {
        this.crustType = crustType;
    }
}
