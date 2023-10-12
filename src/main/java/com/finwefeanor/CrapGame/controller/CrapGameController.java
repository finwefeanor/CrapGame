package com.finwefeanor.CrapGame.controller;

import com.finwefeanor.CrapGame.dto.MultiRoundRequestDTO;
import com.finwefeanor.CrapGame.dto.MultiRoundResponseDTO;
import com.finwefeanor.CrapGame.model.CrapGameResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/play")
public class CrapGameController {
    int roundCounter;

    @GetMapping("/game")
    public ResponseEntity<CrapGameResult> getResult() {
        CrapGameResult result = playSingleRound();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/multi")
    public ResponseEntity<MultiRoundResponseDTO> playMultipleRounds(@RequestBody MultiRoundRequestDTO request) {
        MultiRoundResponseDTO response = new MultiRoundResponseDTO();
        List<CrapGameResult> gameResults = new ArrayList<>();

        int totalStake = 0;
        int totalWin = 0;

        for (int i = 0; i < request.getRounds(); i++) {
            CrapGameResult gameResult = playSingleRound();
            totalStake += gameResult.getStake();
            totalWin += gameResult.getWinning();
            gameResults.add(gameResult);
        }

        response.setTotalStake(totalStake);
        response.setTotalWin(totalWin);
        response.setGameResults(gameResults);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    private CrapGameResult playSingleRound() {
//        CrapGameResult result = new CrapGameResult();
//        result.setDice1((int)(Math.random() * 6 ) +1);
//        result.setDice2((int)(Math.random() * 6 ) +1);
//
//        int dice1Value = result.getDice1();
//        int dice2Value = result.getDice2();
//        int totalSum = dice1Value + dice2Value;
//
//        //first roll
//        if (totalSum == 7 || totalSum == 11) {
//            result.setMessage("You won on the first throw! Wonderful");
//            result.setWinning(result.getStake() + 1);
//            return result;
//        }
//        else if (totalSum == 2 || totalSum == 3 || totalSum == 12) {
//            result.setMessage("You lost on the first throw! Unlucky");
//            result.setWinning(0);
//            return result;
//        }
//        else {
//            int playerPoint = totalSum;
//
//            do {
//                result.setDice1((int)(Math.random() * 6 ) +1);
//                result.setDice2((int)(Math.random() * 6 ) +1);
//                int dice1NextValue = result.getDice1();
//                int dice2NextValue = result.getDice2();
//                totalSum = dice1NextValue + dice2NextValue;
//                result.setMessage("Keep rolling the dices...");
//
//                System.out.println("Rolled: " + dice1Value + " + " + dice2Value + " = " + totalSum);
//                System.out.println("Player Point: " + playerPoint);
//
//                if (totalSum == playerPoint) {
//                    result.setMessage("You Win on the next round, Congrats!");
//                    result.setWinning(result.getStake() + 1);
//                    return result;
//                } else if (totalSum == 7) {
//                result.setMessage("Oh no! You rolled a 7. on the next round, You Lost!");
//                result.setWinning(0);
//                return result;
//                }
//
//            } while (true);
//            //second or next rounds
//
//        }
//    }

    private int[] rollDice() {
        int[] diceRoll = new int[2];
        diceRoll[0] = (int)(Math.random() * 6) + 1;
        diceRoll[1] = (int)(Math.random() * 6) + 1;
        return diceRoll;
    }

    private CrapGameResult playSingleRound() {

        // Increment the Counter at the start of each new round
        roundCounter++;

        // Starting delimiter
        System.out.println("\n---------- Starting Round " + roundCounter + " ----------");

        CrapGameResult result = new CrapGameResult();

        int[] diceRoll = rollDice();
        result.setDice1(diceRoll[0]);
        result.setDice2(diceRoll[1]);

        int totalSum = diceRoll[0] + diceRoll[1];

        System.out.println("Round " + roundCounter + " - First Roll: " + diceRoll[0] + " + " + diceRoll[1] + " = " + totalSum);

        //first roll
        if (totalSum == 7 || totalSum == 11) {
            result.setMessage("You won on the first throw! Wonderful");
            result.setWinning(result.getStake() + 1);
            return result;
        } else if (totalSum == 2 || totalSum == 3 || totalSum == 12) {
            result.setMessage("You lost on the first throw! Unlucky");
            result.setWinning(0);
            return result;
        } else {
            int playerPoint = totalSum;

            while (true) {
                diceRoll = rollDice();
                result.setDice1(diceRoll[0]);
                result.setDice2(diceRoll[1]);
                totalSum = diceRoll[0] + diceRoll[1];

                System.out.println("Round " + roundCounter + " - Subsequent Roll: " + diceRoll[0] + " + " + diceRoll[1] + " = " + totalSum);
                System.out.println("Round " + roundCounter + " - Player Point: " + playerPoint);

                if (totalSum == 7) {
                    result.setMessage("Oh no! You rolled a 7. on the next round, You Lost!");
                    result.setWinning(0);
                    return result;
                } else if (totalSum == playerPoint) {
                    result.setMessage("You Win on the next round, Congrats!");
                    result.setWinning(result.getStake() + 1);
                    return result;
                }
            }
        }
    }


//    private CrapGameResult playSingleRound() {
//        CrapGameResult result = new CrapGameResult();
//
//        int dice1Value = (int)(Math.random() * 6) + 1;
//        int dice2Value = (int)(Math.random() * 6) + 1;
//        result.setDice1(dice1Value);
//        result.setDice2(dice2Value);
//
//        int totalSum = dice1Value + dice2Value;
//
//        // Check for immediate win or loss
//        if (totalSum == 7 || totalSum == 11) {
//            result.setMessage("You win on the first throw! Wonderful");
//            result.setWinning(result.getStake() + 1);
//            return result;
//        } else if (totalSum == 2 || totalSum == 3 || totalSum == 12) {
//            result.setMessage("You lost on the first throw! Unlucky");
//            return result;
//        }
//
//        // If not an immediate win/loss, set the player's point
//        int playerPoint = totalSum;
//
//        // Loop for subsequent rolls
//        while (true) {
//            dice1Value = (int)(Math.random() * 6) + 1;
//            dice2Value = (int)(Math.random() * 6) + 1;
//            totalSum = dice1Value + dice2Value;
//
//            result.setDice1(dice1Value);
//            result.setDice2(dice2Value);
//
//            // Debugging messages
//            System.out.println("Rolled: " + dice1Value + " + " + dice2Value + " = " + totalSum);
//            System.out.println("Player Point: " + playerPoint);
//
//            if (totalSum == 7) {
//                result.setMessage("Oh no! You rolled a 7. You Lost!");
//                result.setWinning(0);
//                return result;
//            }  else if (totalSum == playerPoint) {
//                result.setMessage("You Win! Congrats!");
//                result.setWinning(2);
//                return result;
//            }
//        }
//    }


}
