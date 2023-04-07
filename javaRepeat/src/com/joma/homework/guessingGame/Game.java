package com.joma.homework.guessingGame;

import java.util.*;

public class Game {

    static Scanner scanner = new Scanner(System.in);
    private List<String> cities = new ArrayList<>(List.of("Chicago", "San Fracisco", "New-York"));
    private List<String> animals = new ArrayList<>(List.of("Lion", "Frog", "Elephant"));
    private List<String> vegetables = new ArrayList<>(List.of("Carrot", "Tomato", "Cucumber"));

    public void startGame(Person person) {
        chooseLevel(person);
        boolean usersIntentionToPlay = true;

        while (person.lives >= 1 && usersIntentionToPlay) {
            System.out.println("USER LIVES = "+person.lives);
            String secretWord = generateWord();

                    boolean isGuessed = gameProcess(secretWord, person);

                    if(isGuessed) {
                        System.out.println("Do you wish to continue: ");
                        System.out.println("Press 1, if you wish");
                        System.out.println("Press any other button, if you don't wish");
                        String usersIntentionChoice = scanner.nextLine();
                        if(!usersIntentionChoice.equals("1")){
                            usersIntentionToPlay = false;
                        }
                    }
                    else {
                        System.out.println("Game over! You are out of lives!!!");
                    }
            }
        }

    public boolean gameProcess(String secretWord, Person person) {
        scanner.nextLine();
        // method returning true, means he is done with word, false means he mistyped the word

        // Secret word to show for user
        // h,e,l,l,o
        List<String> secretWordShowToUser =
                new ArrayList<>();

        for(int i = 0 ; i < secretWord.length(); i++){
            secretWordShowToUser.add("_");
        }

        secretWordShowToUser.set(0, secretWord.substring(0,1));
        secretWordShowToUser.set(secretWordShowToUser.size()-1, secretWord.substring(secretWord.length()-1));
        // h,e,l,l,o



        // These are missing letters
        // _, _, _, _, _,
        //e - false
        String[] tempMissingWords = secretWord.split("");
        tempMissingWords[0] = "_";
        tempMissingWords[tempMissingWords.length - 1] = "_";
        List<String> missingWords = Arrays.asList(tempMissingWords);


        String toShowTempWord = String.join("", secretWordShowToUser);
        System.out.println("Your secret word is: " + toShowTempWord);

        // variable for repeating everytime, if user guesses correctly
        while (person.lives>=1) {
            String userGuessInput = "";
            // if this block wasn't in if{} then it would ask for enter even if we are done
            if(secretWordShowToUser.contains("_")){
                System.out.println("Enter your guess character:");
                userGuessInput = scanner.nextLine(); //e
            }
            
            if (missingWords.contains(userGuessInput)) {
                System.out.println("Surprise!, you guessed your character correctly!");
                int index = missingWords.indexOf(userGuessInput); // 1
                secretWordShowToUser.set(index, userGuessInput);
                missingWords.set(index, "_");
                System.out.println("Your word now is : " + String.join("", secretWordShowToUser));
            }
            else if (!secretWordShowToUser.contains("_")) {
                System.out.println("Congratulations, you guessed your word!");
                return true;
            }
            else{
                person.lives = person.lives-1;
                System.out.println("-1 live, you have "+ person.lives+": lives");
            }
        }

        return false;
    }

    public String generateWord() {
        Random random = new Random();
        // 1 2 3
        int listNumber = random.nextInt(1, 4);
        // 0 1 2
        int wordToChoose = random.nextInt(0, 3);

        if(listNumber==1){
            return cities.get(wordToChoose);
        }
        else if(listNumber ==2){
            return animals.get(wordToChoose);

        }
        else{
            return vegetables.get(wordToChoose);
        }
//        String secretWordGeneration = listNumber == 1 ? cities.get(wordToChoose) : listNumber == 2 ?
//                animals.get(wordToChoose) : vegetables.get(wordToChoose);
//        return secretWordGeneration;
    }

    public void chooseLevel(Person person) {
        int difficulty = 0;
        while (!(difficulty == 1 || difficulty == 2 || difficulty == 3)) {
            System.out.println("What difficulty of game you wanna choose?");
            System.out.println("Press 1: for EASY, 6 lives");
            System.out.println("Press 2: for MEDIUM, 3 lives");
            System.out.println("Press 3: for DIFFICULT, 1 lives");
            difficulty = scanner.nextInt();
        }
        if(difficulty == 1){
            person.lives = 6;
        }
        else if (difficulty==2){
            person.lives = 3;
        }
        else {
            person.lives = 1;
        }
    }


}
