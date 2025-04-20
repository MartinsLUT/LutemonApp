package com.example.lutemonapp;



public class Lutemon {

    public String name;
    public String color;
    public int attack;
    public int defense;
    public int experience;
    public int health;
    public int maxHealth;
    public int id;
    private static int idCounter = 0;
    public int wins;
    public int trainings;
    public int battles;

    public Lutemon(String name, String color, int attack, int defense, int experience, int health, int maxHealth, int wins, int trainings, int battles){
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        this.health = health;
        this.maxHealth = maxHealth;
        this.id = idCounter++;
        this.wins = wins;
        this.trainings = trainings;
        this.battles = battles;
    }



    public int attack(){
        return attack;
    }
    public int defense(Lutemon lutemon){
        return lutemon.attack() - defense;


    }
    public static int getNumberOfCreatedLutemons(){
        return idCounter;
    }


    public int getId() {
        return id;
    }



    public String lutemonInfo() {
        return name + "," + color + "," + attack + "," + defense + "," + experience + "," + health + "," + maxHealth + "," + wins + "," + trainings + "," + battles;
    }
}
