package com.finwefeanor.CrapGame.dto;

import com.finwefeanor.CrapGame.model.CrapGameResult;
import lombok.Data;

import java.util.List;

@Data
public class MultiRoundResponse {
    private int totalStake;
    private int totalWin;
    private List<CrapGameResult> gameResults; //Holds the detailed results of each individual game played.
     //allowing player to see the results, dice rolls, and outcome messages for each game.
}
