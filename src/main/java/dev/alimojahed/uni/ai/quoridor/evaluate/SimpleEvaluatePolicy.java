package dev.alimojahed.uni.ai.quoridor.evaluate;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class SimpleEvaluatePolicy implements EvaluatePolicy{
    @Override
    public double evaluate(MiniMaxPlayer player, MiniMaxPlayer opponent) {

        double self_distance = player.bfsToGoal();
        double opponent_distance  = opponent.bfsToGoal();

        return (5 * opponent_distance - self_distance) * (1 + player.walls_count / 2.0);
    }
}
