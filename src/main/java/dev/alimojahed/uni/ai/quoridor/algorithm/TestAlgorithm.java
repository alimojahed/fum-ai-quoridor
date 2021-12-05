package dev.alimojahed.uni.ai.quoridor.algorithm;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class TestAlgorithm extends AdversarialAlgorithm{


    @Override
    protected double algorithm(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        return player.evaluate(opponent);
    }
}
