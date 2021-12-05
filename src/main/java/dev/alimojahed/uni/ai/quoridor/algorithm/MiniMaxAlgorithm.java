package dev.alimojahed.uni.ai.quoridor.algorithm;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class MiniMaxAlgorithm extends AdversarialAlgorithm {

    @Override
    protected double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        return miniMax(player, opponent, player.MAX_DEPTH, false);
    }

    private double miniMax(MiniMaxPlayer player, MiniMaxPlayer opponent,
                           double depth, boolean isMaximizingPlayer) {
        if (Math.abs(depth) < 0.000001) {
            return player.evaluate(opponent);
        }

        double bestActionValue = (isMaximizingPlayer ? -1 : 1) * player.INFINITY;
        for (String action : player.get_legal_actions(opponent)) {
            player.play(action, true);
            bestActionValue = (isMaximizingPlayer ?
                    Math.max(bestActionValue, miniMax(opponent, player, depth - 1, false)) :
                    Math.min(bestActionValue, miniMax(opponent, player, depth - 1, true))
            );
            player.undo_last_action();
        }
        return bestActionValue;
    }


}
