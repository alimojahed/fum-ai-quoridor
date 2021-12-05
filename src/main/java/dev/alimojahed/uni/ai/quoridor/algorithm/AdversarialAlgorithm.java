package dev.alimojahed.uni.ai.quoridor.algorithm;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public interface AdversarialAlgorithm {
    String getBestAction(MiniMaxPlayer player, MiniMaxPlayer opponent);
}
