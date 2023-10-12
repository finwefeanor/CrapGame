package com.finwefeanor.CrapGame.dto;

import com.finwefeanor.CrapGame.model.CrapGameResult;
import lombok.Data;

import java.util.List;

@Data
public class MultiRoundResponseDTO {
    private int totalStake;
    private int totalWin;
    private List<CrapGameResult> gameResults;
}
