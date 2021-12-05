package dev.alimojahed.uni.ai.quoridor.evaluate;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class ManualPositionFeatureSelectionPolicy implements EvaluatePolicy{
    @Override
    public double evaluate(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        //the idea is from https://project.dke.maastrichtuniversity.nl/games/files/bsc/Mertens_BSc-paper.pdf
        /*
            we define the features as follows (based on page 5 and 6 of this paper) :
                f1: position feature -> number of rows the player is away from its initial state
                f2: position difference -> position feature of the max player - position feature of the min player
                f3: max-player's moves to next row -> counting number of moves to the next row raised by -1 because we want to maximize it
                f4: min-player's moves to next row -> counting number of moves to the next row
                w1 = 0.6, w2 = 0.6001, w3 = 14.45, w4 = 6.52
         */
        double f1 = player.getPositionFeature();
        double f2 = player.getPositionFeature() - opponent.getPositionFeature();
        double f3 = 1/player.getPlayerMovesToNextRow();
        double f4 = opponent.getPlayerMovesToNextRow();

        return f1 * 0.6 + f2 * 0.6001 + f3 * 14.45 + f4 * 6.52;
    }
}
