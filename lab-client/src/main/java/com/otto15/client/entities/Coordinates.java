package com.otto15.client.entities;


public class Coordinates {
    private double x; //Максимальное значение поля: 867
    private Double y; //Значение поля должно быть больше -73, Поле не может быть null

    public Coordinates(double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + "]";
    }
}
