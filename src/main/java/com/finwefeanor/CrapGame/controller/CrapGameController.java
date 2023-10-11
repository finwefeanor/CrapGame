package com.finwefeanor.CrapGame.controller;

import com.finwefeanor.CrapGame.model.CrapGameResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/play")
public class CrapGameController {

    @GetMapping("/game")
    public ResponseEntity<CrapGameResult> getResult() {
        CrapGameResult result = new CrapGameResult();

        result.setDice1((int)(Math.random() * 6 ) +1); //math random starts from zero
        result.setDice2((int)(Math.random() * 6 ) +1);

        // Retrieve the values from model
        int dice1Value = result.getDice1();
        int dice2Value = result.getDice2();

        // win/lose conditions
        if (dice1Value + dice2Value == 7 || dice1Value + dice2Value == 11) {
            result.setMessage("You win on the first throw! Wonderful");
            result.setWinning(result.getStake() + 1);
        }
        else if (dice1Value + dice2Value == 2 || dice1Value + dice2Value == 3 || dice1Value + dice2Value == 12)
        {
            result.setMessage("You lost on the first throw! Unlucky");
        }
        else
        {
            int playerPoint = dice1Value + dice2Value;
            int dice1NextRollValue;
            int dice2NextRollValue;
            int totalSum;

            do {
                result.setDice1((int)(Math.random() * 6 ) +1);
                result.setDice2((int)(Math.random() * 6 ) +1);
                dice1NextRollValue = result.getDice1();
                dice2NextRollValue = result.getDice2();
                totalSum = dice1NextRollValue + dice2NextRollValue;

            } while (totalSum != 7 && totalSum != playerPoint);

            if (totalSum == playerPoint) {
                result.setMessage("You Win! Congrats!");
                result.setWinning(result.getStake() + 1);

            } else if (totalSum == 7) {
                result.setMessage("You Lost! Play Again");
                result.setWinning(0);
            }

        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
