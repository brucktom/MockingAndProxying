package de.oth.mocker;

import static de.oth.mocker.FrequencyType.*;

public class Frequency {

    public Frequency (int amount, FrequencyType frequencyType) {
        this.amount = amount;
        this.frequencyType = frequencyType;
    }

    private int amount;
    private FrequencyType frequencyType;


    public static Frequency never() {
        return new Frequency(0, NEVER);
    }

    public static Frequency atMost(int amount) {
        return new Frequency(amount, ATMOST);
    }

    public static Frequency atLeast(int amount) {
        return new Frequency(amount, ATLEAST);
    }

    public static Frequency times(int amount) {
        return new Frequency(amount, TIMES);
    }

    public FrequencyType getFrequencyType() {return frequencyType;}

    public void setFrequencyType(FrequencyType frequencyType) {this.frequencyType = frequencyType;}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }






}
