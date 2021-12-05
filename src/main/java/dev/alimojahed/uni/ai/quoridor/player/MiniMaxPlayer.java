package dev.alimojahed.uni.ai.quoridor.player;

import dev.alimojahed.uni.ai.quoridor.algorithm.search.adversarial.AdversarialSearchAlgorithm;
import dev.alimojahed.uni.ai.quoridor.evaluate.EvaluatePolicy;
import dev.alimojahed.uni.ai.quoridor.game.Board;
import dev.alimojahed.uni.ai.quoridor.game.Piece;

import java.util.*;

public class MiniMaxPlayer extends Player {

    private final AdversarialSearchAlgorithm adversarialAlgorithm;
    private final EvaluatePolicy evaluatePolicy;
    public double MAX_DEPTH = 2.0;
    public double INFINITY = 9999.0;

    public MiniMaxPlayer(String color, int x, int y, Board board,
                         AdversarialSearchAlgorithm adversarialAlgorithm,
                         EvaluatePolicy evaluatePolicy) {

        super(color, x, y, board);

        this.adversarialAlgorithm = adversarialAlgorithm;
        this.evaluatePolicy = evaluatePolicy;
    }


    public double bfs(Set<Piece> destination) {
        MiniMaxPlayer player = this;
        double self_distance = 0.0;
        Queue<Piece> queue = new LinkedList<Piece>();
        HashMap<Piece, Boolean> visited = new HashMap<Piece, Boolean>();
        HashMap<Piece, Double> distances = new HashMap<Piece, Double>();

        for (int y = 0; y < this.board.ROWS_NUM; y++) {
            for (int x = 0; x < this.board.COLS_NUM; x++) {
                visited.put(this.board.boardMap[y][x], false);
                distances.put(this.board.boardMap[y][x], this.INFINITY);
            }
        }

        String player_pos = player.get_position();
        int x = Integer.parseInt(player_pos.split(",")[0]);
        int y = Integer.parseInt(player_pos.split(",")[1]);
        Piece player_piece = this.board.get_piece(x, y);

        queue.add(player_piece);
        visited.put(player_piece, true);
        distances.put(player_piece, 0.0);

        while (queue.size() != 0) {
            Piece piece = ((LinkedList<Piece>) queue).removeFirst();

            Set<Piece> piece_temp = new HashSet<Piece>();

            piece_temp = this.board.get_piece_neighbors(piece);
            for (Piece p : piece_temp) {
                if (!visited.get(p)) {
                    double t = distances.get(piece) + 1;
                    distances.put(p, t);
                    visited.put(p, true);
                    queue.add(p);
                }
            }
            double min_distance = this.INFINITY;
            for (Piece p_key : distances.keySet()) {
                if (destination.contains(p_key)) {
                    if (distances.get(p_key) < min_distance) {
                        min_distance = distances.get(p_key);
                    }
                }
            }
            self_distance = min_distance;
        }

        return self_distance;
    }

    public double bfsToGoal() {
        double self_distance = 0.0;
        double opponent_distance = 0.0;

        MiniMaxPlayer player = this;
        Set<Piece> destination = new HashSet<Piece>();
        if (player.color.equals("white")) destination = this.board.get_white_goal_pieces();
        else destination = this.board.get_black_goal_pieces();

        return bfs(destination);
    }

    public double evaluate(MiniMaxPlayer opponent) {
        return evaluatePolicy.evaluate(this, opponent);
    }

    public String get_best_action(MiniMaxPlayer opponent) {
        return adversarialAlgorithm.getBestAction(this, opponent);
    }

    public double getPositionFeature() {
        if (this.color.equals("white")) {
            return -(y - initialY);
        }
        return y - initialY;
    }

    public double getPlayerMovesToNextRow() {
        if (color.equals("white") && y == 0 || color.equals("black") && y == board.ROWS_NUM-1) {
            return 1;
        }

        int nextRowNumber;

        if (color.equals("white"))
            nextRowNumber = y - 1;
        else
            nextRowNumber = y + 1;


        return bfs(board.getNextRowPieces(nextRowNumber));
    }

}
