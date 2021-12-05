package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/

@NoArgsConstructor
@Getter
public class TranspositionTable {

    private Map<String, Item> items = new HashMap<>();

    public boolean hasItem(MiniMaxPlayer player, boolean isMaximizingPlayer, double depth) {
        return items.containsKey(hash(player, isMaximizingPlayer, depth));
    }

    public double getCachedState(MiniMaxPlayer player, boolean isMaximizingPlayer, double depth) {
        Item defaultItem = new Item("", 0, 0);

        return items.getOrDefault(hash(player, isMaximizingPlayer, depth), defaultItem).evaluate;
    }

    public void put(MiniMaxPlayer player, boolean isMaximizingPlayer, double depth, double evaluate) {
        String key = hash(player, isMaximizingPlayer, depth);
        items.put(key, new Item(key, depth, evaluate));

    }

    private String hash(MiniMaxPlayer player, boolean isMaximizingPlayer, double depth) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(depth)
                .append(isMaximizingPlayer)
                .append(player.board.toString());

        return stringBuilder.toString();
    }

    @AllArgsConstructor
    private class Item {
        String key;
        double evaluate;
        double depth;
    }

}
