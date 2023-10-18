package com.finwefeanor.CrapGame.service;

import com.finwefeanor.CrapGame.dto.MultiRoundRequest;
import com.finwefeanor.CrapGame.dto.MultiRoundResponse;
import com.finwefeanor.CrapGame.model.CrapGameResult;
import com.finwefeanor.CrapGame.model.RollResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer containing the core logic of the Crap game.
 * This class handles the game mechanics, freeing up the controller to manage HTTP interactions.
 */

@Service
public class CrapGameServiceImpl implements CrapGameService{

    private int roundCounter = 0;


    /*
     * Simulates a dice roll, producing a random number between 1 and 6 for each die.
     *
     * @return An array with two integers representing the dice rolls.
     */
    private int[] rollDice() {
        int[] diceRoll = new int[2];
        diceRoll[0] = (int)(Math.random() * 6) + 1; //random starts from zero
        diceRoll[1] = (int)(Math.random() * 6) + 1;
        return diceRoll;
    }

    /**
     * Handles the logic for a single round of the Crap game.
     *
     * @return A CrapGameResult object containing the outcome of the round.
     */
    @Override
    public CrapGameResult playSingleRound() {

        // Increment the Counter at the start of each new round
        roundCounter++;
        // Reset the rollCounter for each new game
        int rollCounter = 1;

        System.out.println("\n---------- Starting Round " + roundCounter + " ----------");

        CrapGameResult result = new CrapGameResult();

        result.setRolls(new ArrayList<RollResult>());

        int[] diceRoll = rollDice();
        result.setDice1(diceRoll[0]);
        result.setDice2(diceRoll[1]);

        int totalSum = diceRoll[0] + diceRoll[1];

        System.out.println("Game " + roundCounter + " - Roll " + rollCounter + ": " + diceRoll[0] + " + " + diceRoll[1] + " = " + totalSum);

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

            // Print player point once after it's set
            System.out.println("PlayerPoint for Game " + roundCounter + " is : " + playerPoint);

            while (true) {
                diceRoll = rollDice();

                // Create a RollResult instance for this roll
                RollResult currentRoll = new RollResult();
                currentRoll.setDice1(diceRoll[0]);
                currentRoll.setDice2(diceRoll[1]);
                totalSum = diceRoll[0] + diceRoll[1]; ;
                currentRoll.setMessage("Continue to roll");
                currentRoll.setRollNumber(rollCounter);
                currentRoll.setPlayerPointNumber(playerPoint);

                currentRoll.setSum(totalSum);

                rollCounter++; // Increment roll counter
                System.out.println("Game " + roundCounter + " - Roll " + rollCounter + ": " + diceRoll[0] + " + " + diceRoll[1] + " = " + totalSum);

                // Always add the currentRoll to result's rolls list
                result.getRolls().add(currentRoll);

                if (totalSum == 7)  {
                    currentRoll.setMessage("Oh no! You rolled a 7 on this roll, and your Player Point was " + playerPoint + ", You Lost!");
                    result.setMessage("You lost this round by rolling a 7, you need to match the Player Point");
                    result.setWinning(0);
                    return result;
                }
                else if (totalSum == playerPoint) {
                    currentRoll.setMessage("You matched the roll sum with your player point of " + playerPoint + " on this roll. Congrats!");
                    result.setMessage("You have Won this round by matching the Player Point! Congrats!");
                    result.setWinning(result.getStake() + 1);
                    return result;
                }
            }
        }
    }


    /*

     * A single game can involve multiple dice rolls, but it has
     * one of three outcomes: a win on the first roll, a win on a subsequent roll,
     * or a loss. This multi method executes n number of these games and collects
     * the results.
     */
    @Override
    public MultiRoundResponse playMultipleRounds(MultiRoundRequest request) {

        // Create a response object to collate and store the results of all played games.
        MultiRoundResponse response = new MultiRoundResponse();

        List<CrapGameResult> gameResults = new ArrayList<>();

        int totalStake = 0;
        int totalWin = 0;

        // Iterate for the number of games the player wishes to play.
        for (int i = 0; i < request.getGames(); i++) {
            // For each iteration, play a single game of Craps and store the result.
            CrapGameResult gameResult = playSingleRound();

            gameResult.setGameId(i + 1);


            // Update the running totals for stake and winnings.
            totalStake += gameResult.getStake();
            totalWin += gameResult.getWinning();
            gameResults.add(gameResult);
        }

        response.setTotalStake(totalStake);
        response.setTotalWin(totalWin);
        response.setGameResults(gameResults);

        return response;
    }
}
