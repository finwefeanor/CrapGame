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

    private CrapGameResult playSingleRound() {
        CrapGameResult result = new CrapGameResult();
        result.setDice1((int)(Math.random() * 6 ) +1);
        result.setDice2((int)(Math.random() * 6 ) +1);

        int dice1Value = result.getDice1();
        int dice2Value = result.getDice2();

        if (dice1Value + dice2Value == 7 || dice1Value + dice2Value == 11) {
            result.setMessage("You win on the first throw! Wonderful");
            result.setWinning(result.getStake() + 1);
        }
        else if (dice1Value + dice2Value == 2 || dice1Value + dice2Value == 3 || dice1Value + dice2Value == 12) {
            result.setMessage("You lost on the first throw! Unlucky");
            result.setWinning(0);
        }
        else {
            int playerPoint = dice1Value + dice2Value;
            int totalSum;

            do {
                result.setDice1((int)(Math.random() * 6 ) +1);
                result.setDice2((int)(Math.random() * 6 ) +1);
                dice1Value = result.getDice1();
                dice2Value = result.getDice2();
                totalSum = dice1Value + dice2Value;
                result.setMessage("Keep rolling the dices...");

            } while (totalSum != 7 && totalSum != playerPoint);

            if (totalSum == playerPoint) {
                result.setMessage("You Win! Congrats!");
                result.setWinning(result.getStake() + 1);
            } else if (totalSum == 7) {
                result.setMessage("You Lost! Play Again");
                result.setWinning(0);
            }
        }
        return result;
    }


}
