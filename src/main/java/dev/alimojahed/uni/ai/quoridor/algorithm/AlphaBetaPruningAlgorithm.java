package dev.alimojahed.uni.ai.quoridor.algorithm;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class AlphaBetaPruningAlgorithm extends AdversarialAlgorithm{
    @Override
    protected double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        return alphaBetaPruning(player, opponent, player.MAX_DEPTH,
                false, -player.INFINITY, player.INFINITY);
    }

    private double alphaBetaPruning(MiniMaxPlayer player, MiniMaxPlayer opponent,
                                    double depth, boolean isMaximizingPlayer,
                                    double alpha, double beta) {
        if (Math.abs(depth) == 0) {
            return player.evaluate(opponent);
        }

        double bestActionValue = (isMaximizingPlayer ? -1 : 1) * player.INFINITY;
        for (String action : player.get_legal_actions(opponent)) {
            player.play(action, true);
            bestActionValue = (isMaximizingPlayer ?
                    Math.max(bestActionValue, alphaBetaPruning(opponent, player, depth - 1, false, alpha, beta)) :
                    Math.min(bestActionValue, alphaBetaPruning(opponent, player, depth - 1, true, alpha, beta))
            );
            player.undo_last_action();
            if (isMaximizingPlayer) {
                alpha = Math.max(alpha, bestActionValue);
            }else {
                beta = Math.min(beta, bestActionValue);
            }
            if (alpha >= beta) {
                break;
            }

        }
        return bestActionValue;

    }

}
