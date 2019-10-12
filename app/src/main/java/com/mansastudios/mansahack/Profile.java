package com.mansastudios.mansahack;

public class Profile {
    String Name;
    String id;

    public Profile(String name, String id) {
        Name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
}
