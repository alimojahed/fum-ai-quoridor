package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public abstract class AdversarialSearchAlgorithm {

    public String getBestAction(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        double bestActionValue = -Double.MAX_VALUE;
        String bestAction = null;
        boolean getNewActionFlag = true;
        boolean isActionChosen = false;
        boolean hasDuplicateAction = false;
        String duplicatedAction = null;
        for (String action : player.get_legal_actions(opponent)) {
            player.play(action, true);

            double tmpValue = algorithm(player, opponent);
            player.undo_last_action();
//            System.out.println(tmpValue);

            if (tmpValue > bestActionValue) {

                if (!player.firstMove && action.equals(player.parentToThisAction)) {
                    duplicatedAction = action;
                    hasDuplicateAction = true;
                    player.duplicateActionHappened++;
                    System.out.println("DUPLICATE ACTION HAPPENED");
                    if (!player.canPerformDuplicateMove()) {
                        continue;
                    }

                }

                bestActionValue = tmpValue;
                bestAction = action;
                isActionChosen = true;
            }
        }

        if (!isActionChosen) {
            bestAction = duplicatedAction;
        }

        if (player.firstMove) {
            player.firstMove = false;
        }else {
            player.parentToThisAction = player.lastAction;
        }



        System.out.println(bestAction);
        System.out.println(bestActionValue);

        player.lastAction = bestAction;
        System.out.println(player.parentToThisAction);
        System.out.println(player.lastAction);
        return bestAction;
    }

    protected abstract double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent);

}
