package com.finwefeanor.CrapGame.controller;

import com.finwefeanor.CrapGame.dto.MultiRoundRequest;
import com.finwefeanor.CrapGame.dto.MultiRoundResponse;
import com.finwefeanor.CrapGame.model.CrapGameResult;
import com.finwefeanor.CrapGame.model.RollResult;
import com.finwefeanor.CrapGame.service.CrapGameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/play")
public class CrapGameController {
    @Autowired
    private final CrapGameService crapGameService;

    @Autowired
    public CrapGameController(CrapGameService crapGameService) {
        this.crapGameService = crapGameService;
    }
    private int roundCounter;

    @GetMapping("/game")
    public ResponseEntity<CrapGameResult> getResult() {
        CrapGameResult result = crapGameService.playSingleRound();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/multi")
    public ResponseEntity<MultiRoundResponse> playMultipleRounds(@RequestBody @Valid MultiRoundRequest request) {

        MultiRoundResponse response = crapGameService.playMultipleRounds(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
