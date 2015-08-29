package com.mastersofcode;

/**
 * Created by T on 2015/08/22.
 */
public class Product
{
    private String name;
    private String price;
    private String quantity;

    public Product(String name, String price, String quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getPrice()
    {
        return this.price;
    }

    public void setQuantity(String quantity)
    {
        this.quantity = quantity;
    }

    public String getQuantity()
    {
        return this.quantity;
    }
}
