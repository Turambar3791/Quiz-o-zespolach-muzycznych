package com.example.aplikcjacrud.model;

import com.example.aplikcjacrud.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    private UUID id;
    private String correctAnswer;
    private String[] questions;
    private int image;

    public Question(String correctAnswer, String[] questions, int image) {
        this.id = UUID.randomUUID();
        this.correctAnswer = correctAnswer;
        this.questions = questions;
        this.image = image;
    }

    public static List<Question> generateList() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Queen", new String[]{"Queen", "The Beatles"}, R.drawable.queen));
        questions.add(new Question("Alice in Chains", new String[]{"Alice in Chains", "Pearl Jam"}, R.drawable.aic));
        questions.add(new Question("Lordi", new String[]{"Slipknot", "Lordi"}, R.drawable.lordi));
        questions.add(new Question("Foo Fighters", new String[]{"Red Hot Chili Peppers", "Foo Fighters"}, R.drawable.foofighters));
        questions.add(new Question("Aerosmith", new String[]{"Guns N' Roses", "Aerosmith"}, R.drawable.aerosmith));
        questions.add(new Question("Dżem", new String[]{"Dżem", "Breakout"}, R.drawable.dzem));
        questions.add(new Question("Nirvana", new String[]{"Nirvana", "Alice in Chains"}, R.drawable.nirvana));
        questions.add(new Question("Guns N' Roses", new String[]{"Guns N' Roses", "Velvet Revolver"}, R.drawable.gnr));
        questions.add(new Question("Metallica", new String[]{"Metallica", "Megadeth"}, R.drawable.metallica));
        questions.add(new Question("Thirty Seconds To Mars", new String[]{"My Chemical Romance", "Thirty Seconds To Mars"}, R.drawable.thirtysecondstomars));
        questions.add(new Question("Bon Jovi", new String[]{"Bon Jovi", "Europe"}, R.drawable.bonjovi));
        questions.add(new Question("Black Sabbath", new String[]{"Black Sabbath", "Sabaton"}, R.drawable.blacksabbath));
        questions.add(new Question("AC/DC", new String[]{"AC/DC", "Iron Maiden"}, R.drawable.acdc));
        questions.add(new Question("Led Zeppelin", new String[]{"Led Zeppelin", "Def Leppard"}, R.drawable.ledzeppelin));
        questions.add(new Question("Pearl Jam", new String[]{"Pearl Jam", "Soundgarden"}, R.drawable.pearljam));
        questions.add(new Question("Kiss", new String[]{"Kiss", "Behemoth"}, R.drawable.kiss));
        questions.add(new Question("Ira", new String[]{"Ira", "Budka Suflera"}, R.drawable.ira));
        questions.add(new Question("The Beatles", new String[]{"The Kinks", "The Beatles"}, R.drawable.thebeatles));
        questions.add(new Question("Genesis", new String[]{"The Police", "Genesis"}, R.drawable.genesis));
        questions.add(new Question("ZZ Top", new String[]{"Dżem", "ZZ Top"}, R.drawable.zztop));

        return questions;
    }

    public UUID getId() {
        return id;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getQuestions() {
        return questions;
    }

    public int getImage() {
        return image;
    }
}
