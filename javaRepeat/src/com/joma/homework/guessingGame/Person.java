package com.joma.homework.guessingGame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Person {

    int lives = 0;

    List<Character> previouslyGuessed;

    public Person() {
        this.previouslyGuessed = new ArrayList<>();
    }

}
