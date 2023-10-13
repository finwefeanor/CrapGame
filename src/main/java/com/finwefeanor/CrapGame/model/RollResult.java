package com.finwefeanor.CrapGame.model;

import lombok.Data;

@Data
public class RollResult {
    private int dice1;
    private int dice2;
    private int sum;
    private String message;
    private int rollNumber; // to count rolls inside json
    private int playerPointNumber; // to count player point inside json
}
