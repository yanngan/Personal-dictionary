package com.ymg.personaldictionary.logic;

public class Word {
    public String id;
    public String source;
    public String meaning;
    public String description;
    public String score;

    public Word(String id, String source, String meaning, String description, String score){
        this.id = id;
        this.source = source;
        this.meaning = meaning;
        this.description = description;
        this.score = score;
    }

}
