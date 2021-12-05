package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public abstract class AdversarialSearchAlgorithm {

    public String getBestAction(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        double bestActionValue = Double.MIN_VALUE;
        String bestAction = null;
        for (String action : player.get_legal_actions(opponent)) {
            player.play(action, true);

            double tmpValue = algorithm(player, opponent);
            player.undo_last_action();
            if (tmpValue > bestActionValue) {
                bestActionValue = tmpValue;
                bestAction = action;
            }
        }
        return bestAction;
    }

    protected abstract double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent);

}
