package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class TestAlgorithm extends AdversarialSearchAlgorithm {


    @Override
    protected double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        return player.evaluate(opponent);
    }
}
