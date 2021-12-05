package dev.alimojahed.uni.ai.quoridor.evaluate;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

import java.util.Random;

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
            we define the features as follows (based on page 5 and 6 of this paper with some modification) :
                f1: position feature -> number of rows the player is away from its initial state
                f3: max-player's moves to next row -> counting number of moves to the next row raised by -1 because we want to maximize it
                f4: min-player's moves to next row -> counting number of moves to the next row
                w3 = 14.45, w4 = 6.52
         */
        double f1 = player.getPositionFeature();
        double f2 = player.bfsToGoal();
        double f3 = 1/player.getPlayerMovesToNextRow();
        double f4 = opponent.getPlayerMovesToNextRow();
        double f5 = opponent.bfsToGoal();
        int numberOfWallsLeftWeight = player.walls_count > 0 ? 1 : 0;

        return (f1 * 2  + f3 * 14.45 + f4 * 6.52) * numberOfWallsLeftWeight + f2 * -5 + f5 * 3;
    }
}
