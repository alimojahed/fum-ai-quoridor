package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class AlphaBetaPruningWithTranspositionTable extends AlphaBetaPruningAlgorithm{
    private TranspositionTable transpositionTable = new TranspositionTable();



    @Override
    protected double alphaBetaPruning(MiniMaxPlayer player, MiniMaxPlayer opponent, double depth, boolean isMaximizingPlayer, double alpha, double beta) {
        if (transpositionTable.hasItem(player, isMaximizingPlayer, depth)) {
            return transpositionTable.getCachedState(player, isMaximizingPlayer, depth);
        }

        if (depth == 0) {
            double evaluate = player.evaluate(opponent);
            transpositionTable.put(player, isMaximizingPlayer, depth, evaluate);
            return evaluate;
        }

        double bestValueAction = SimpleAlphaBetaPruningAlgorithm(player, opponent, depth, isMaximizingPlayer, alpha, beta);

        transpositionTable.put(player, isMaximizingPlayer, depth, bestValueAction);

        return bestValueAction;
    }
}
