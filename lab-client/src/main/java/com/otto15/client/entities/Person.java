package com.otto15.client.entities;

import com.otto15.client.entities.enums.Color;
import com.otto15.client.entities.enums.Country;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Person implements Comparable<Person> {
    private static Long previousId = 0L;
    private final Long id = previousId++ + 1; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final ZonedDateTime creationDate = ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Coordinates coordinates; //Поле не может быть null //

    private long height; //Значение поля должно быть больше 0 //

    private Color eyeColor; //Поле может быть null

    private Color hairColor; //Поле может быть null

    private Country nationality; //Поле не может быть null

    private Location location; //Поле не может быть null

    public Person(String name, Coordinates coordinates, long height, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public static Long getPreviousId() {
        return previousId;
    }

    public static void setPreviousId(Long newPreviousId) {
        previousId = newPreviousId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    @Override
    public int compareTo(Person anotherPerson) {
        return Comparator.comparing(Person::getName).thenComparingLong(Person::getHeight).compare(this, anotherPerson);
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", coordinates: " + coordinates + ", height: " + height + ", creationDate: " + creationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm")) + ", eyeColor: " + eyeColor + ", hairColor: " + hairColor + ", nationality: " + nationality + ", location: " + location;
    }
}
