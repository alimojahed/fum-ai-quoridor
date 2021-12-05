package dev.alimojahed.uni.ai.quoridor.evaluate;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public interface EvaluatePolicy {
    double evaluate(MiniMaxPlayer player, MiniMaxPlayer opponent);
}
