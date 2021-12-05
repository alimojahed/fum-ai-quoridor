package dev.alimojahed.uni.ai.quoridor;

import dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial.AlphaBetaPruningAlgorithm;
import dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial.AlphaBetaPruningWithForwardPruningAndTTAlgorithm;
import dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial.AlphaBetaPruningWithTranspositionTable;
import dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial.TranspositionTable;
import dev.alimojahed.uni.ai.quoridor.evaluate.ManualPositionFeatureSelectionPolicy;
import dev.alimojahed.uni.ai.quoridor.game.Board;
import dev.alimojahed.uni.ai.quoridor.player.MiniMaxPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();

        //TODO: for using different algorithm you can change the passing algorithm in player constructors
        MiniMaxPlayer white_player = new MiniMaxPlayer("white", 4, 8, board, new AlphaBetaPruningWithForwardPruningAndTTAlgorithm(), new ManualPositionFeatureSelectionPolicy());
        MiniMaxPlayer black_player = new MiniMaxPlayer("black", 4, 0, board, new AlphaBetaPruningWithForwardPruningAndTTAlgorithm(), new ManualPositionFeatureSelectionPolicy());
        Set<String> move = new HashSet<String>();

        int walls_count = 0;

        while (true) {
            String action = white_player.get_best_action(black_player);

            white_player.play(action, false);
            board.print_map();
            System.out.println(
                    "white: " + action + ", evaluation: " + white_player.evaluate(black_player) +
                            ", left walls: " + white_player.walls_count
            );


            if (white_player.is_winner()) {
                System.out.println("White player just won with " + white_player.moves_count + " moves!");
                break;
            }
            if (action.split("#")[0].equals("wall")) walls_count += 1;

            TimeUnit.SECONDS.sleep(3);

            action = black_player.get_best_action(white_player);

            black_player.play(action, false);
            board.print_map();
            System.out.println(
                    "black: " + action + ", evaluation: " + black_player.evaluate(white_player) +
                            ", left walls: " + black_player.walls_count
            );

            if (black_player.is_winner()) {
                System.out.println("Black player just won with " + black_player.moves_count + " moves!");
                break;
            }

            if (action.split("#")[0].equals("wall")) walls_count += 1;

            TimeUnit.SECONDS.sleep(3);
        }
    }
}
