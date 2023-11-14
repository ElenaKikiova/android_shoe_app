package com.example.shoes;

public class Shoe {
    protected Integer ID;
    protected String Name;
    protected String ImageScr;
    protected Float Price;
    protected Integer Quantity;
    protected String DateAdded;

    public Shoe(Integer id, String name, String imageSrc, Float price, Integer quantity, String dateAdded) {
        this.ID = id;
        this.Name = name;
        this.ImageScr = imageSrc;
        this.Price = price;
        this.Quantity = quantity;
        this.DateAdded = dateAdded;
    }

    public Integer getID(){
        return this.ID;
    }
    public String getName(){
        return this.Name;
    }
    public String getImageSrc(){
        return this.ImageScr;
    }
    public Float getPrice(){
        return this.Price;
    }
    public Integer getQuantity(){
        return this.Quantity;
    }
    public String getDateAdded(){
        return this.DateAdded;
    }

}
