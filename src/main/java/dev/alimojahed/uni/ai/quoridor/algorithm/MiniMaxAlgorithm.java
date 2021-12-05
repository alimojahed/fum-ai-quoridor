package dev.alimojahed.uni.ai.quoridor.algorithm;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class MiniMaxAlgorithm implements AdversarialAlgorithm{
    @Override
    public String getBestAction(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        return null;
    }

    private double miniMax(MiniMaxPlayer player, MiniMaxPlayer opponent,
                                            double depth, boolean isMaximizingPlayer) {
        if (Math.abs(depth) < 0.000001) {
            return player.evaluate(opponent);
        }

        double bestActionValue = (isMaximizingPlayer ? -1 : 1) * player.INFINITY;

        return 0;
    }

}
