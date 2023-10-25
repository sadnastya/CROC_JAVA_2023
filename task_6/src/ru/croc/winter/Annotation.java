package ru.croc.winter;

public abstract class Annotation {
    protected String figure;
    protected String signature;

    Annotation(String figure, String signature){
        this.figure = figure;
        this.signature = signature;

    }
}
