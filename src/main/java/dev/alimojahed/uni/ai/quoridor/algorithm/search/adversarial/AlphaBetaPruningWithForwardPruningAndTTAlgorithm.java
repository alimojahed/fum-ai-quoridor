package dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial;

import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: alimojahed
 * @date: 05.12.21
 * @project: fum-ai-quoridor
 **/


public class AlphaBetaPruningWithForwardPruningAndTTAlgorithm extends AlphaBetaPruningAlgorithm{
    private TranspositionTable table = new TranspositionTable();
    private final int BEAM_CUTOFF = 5;

    @Override
    protected double alphaBetaPruning(MiniMaxPlayer player, MiniMaxPlayer opponent, double depth, boolean isMaximizingPlayer, double alpha, double beta) {

        if (table.hasItem(player, isMaximizingPlayer, depth)) {
            return table.getCachedState(player, isMaximizingPlayer, depth);
        }

        if (depth == 0) {
            double evaluate = player.evaluate(opponent);
            table.put(player, isMaximizingPlayer, depth, evaluate);
            return evaluate;
        }

        List<String> actions = forwardPruningActions(player, opponent);
        double bestActionValue = simpleAlphaBetaPruningAlgorithm(player, opponent, depth,
                isMaximizingPlayer, alpha, beta, actions);

        table.put(player, isMaximizingPlayer, depth, bestActionValue);

        return bestActionValue;
    }

    private List<String> forwardPruningActions(MiniMaxPlayer player, MiniMaxPlayer opponent) {
        Set<ActionValueWrapper> tmpContainer = new HashSet<>();
        for (String action: player.get_legal_actions(opponent)) {
            player.play(action, true);
            tmpContainer.add(new ActionValueWrapper(action, player.evaluate(opponent)));
            player.undo_last_action();
        }

        List<ActionValueWrapper> actionValueWrappers = tmpContainer.stream().toList();

        return actionValueWrappers.stream()
                .sorted(Comparator.comparing(actionValueWrapper -> actionValueWrapper.value))
                .limit(BEAM_CUTOFF)
                .map(actionValueWrapper -> actionValueWrapper.action)
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    private class ActionValueWrapper {
        String action;
        double value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ActionValueWrapper that = (ActionValueWrapper) o;
            return action.equals(that.action);
        }

        @Override
        public int hashCode() {
            return Objects.hash(action);
        }
    }
}
