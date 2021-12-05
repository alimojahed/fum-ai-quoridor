package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

import java.util.Collection;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class AlphaBetaPruningAlgorithm extends AdversarialSearchAlgorithm {
    @Override
    protected double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        return alphaBetaPruning(player, opponent, player.MAX_DEPTH,
                false, -player.INFINITY, player.INFINITY);
    }

    protected double alphaBetaPruning(MiniMaxPlayer player, MiniMaxPlayer opponent,
                                    double depth, boolean isMaximizingPlayer,
                                    double alpha, double beta) {
        if (Math.abs(depth) == 0) {
            return player.evaluate(opponent);
        }

        return simpleAlphaBetaPruningAlgorithm(player, opponent, depth, isMaximizingPlayer, alpha, beta, player.get_legal_actions(opponent));

    }

    protected double simpleAlphaBetaPruningAlgorithm(MiniMaxPlayer player, MiniMaxPlayer opponent,
                                                     double depth, boolean isMaximizingPlayer,
                                                     double alpha, double beta, Collection<String> actions) {
        double bestActionValue = (isMaximizingPlayer ? -1 : 1) * player.INFINITY;
        for (String action : actions) {
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
