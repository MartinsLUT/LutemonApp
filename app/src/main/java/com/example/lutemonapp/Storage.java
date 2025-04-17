package com.example.lutemonapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    public String name;

    public static ArrayList<Lutemon> lutemons = new ArrayList<>();

    public static void addLutemon(Lutemon lutemon) {

        lutemons.add(lutemon);
    }

    public Lutemon getLutemons(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.id == id) {
                return lutemon;
            }
        }
        return null;
    }

    public void listLutemons() {
        for (Lutemon lutemon : lutemons) {
            System.out.println(lutemon.id + ": " + lutemon.color + " (" + lutemon.name + ")");
        }
    }
}
