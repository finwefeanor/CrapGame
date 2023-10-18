package com.finwefeanor.CrapGame.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MultiRoundRequest {

    @Min(value = 1, message = "Number of games must be at least 1")
    @Max(value = 100, message = "Number of games must be no more than 100")
    private int games; //n number of game
}
