package com.finwefeanor.CrapGame.service;

import com.finwefeanor.CrapGame.dto.MultiRoundRequest;
import com.finwefeanor.CrapGame.dto.MultiRoundResponse;
import com.finwefeanor.CrapGame.model.CrapGameResult;

public interface CrapGameService {
    CrapGameResult playSingleRound();
    MultiRoundResponse playMultipleRounds(MultiRoundRequest request);
}
