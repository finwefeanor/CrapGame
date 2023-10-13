package com.finwefeanor.CrapGame.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CrapGameResult {

    private int gameId; //to count game in json

    private int dice1;
    private int dice2;

    private int stake = 1;

    private int winning;
    private List<RollResult> rolls = new ArrayList<>();

    private String message = "Welcome to the Dice Game";
}
