package com.blingo.lingdyo;

import java.util.ArrayList;

public class RegisteredUser extends User{
    private int id;
    private String name;
    private int password;
    private ArrayList<Integer> friendsId;

    public RegisteredUser(int id, String name, int password, ArrayList<Integer> friendsId){
        this.id = id;
        this.name = name;
        this.password = password;
        this.friendsId = friendsId;}
    public void logOut(){}
    public void createCourse(String name){}
    public void addSentence(){}
    public void addExample(){}
}
