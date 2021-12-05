package dev.alimojahed.uni.ai.quoridor.algorithm;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class TestAlgorithm implements AdversarialAlgorithm{



    @Override
    public String getBestAction(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        double best_action_value = - (player.INFINITY);
        String best_action = "";
        Set<String> legal_move = new HashSet<String>();
        legal_move = player.get_legal_actions(opponent);
        for (String action : legal_move) {
            player.play(action, true);
            if (player.is_winner()){
                player.undo_last_action();
                return action;
            }

            double action_value = player.evaluate(opponent);
            if (action_value > best_action_value){
                best_action_value = action_value;
                best_action = action;
            }

            player.undo_last_action();
        }

        return best_action;
    }
}
