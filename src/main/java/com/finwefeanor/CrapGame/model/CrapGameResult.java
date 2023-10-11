package com.finwefeanor.CrapGame.model;

import lombok.Data;

@Data
public class CrapGameResult {

    private int dice1;
    private int dice2;

    private int stake = 1;

    private int winning;

    private String message = "Hello Dice";
}
