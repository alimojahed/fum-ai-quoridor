package dev.alimojahed.uni.ai.quoridor.game;

public class Piece {
    public int x;
    public int y;
    public String state;
    public String r_side;
    public String u_side;
    public String l_side;
    public String d_side;
    public boolean is_white_goal;
    public boolean is_black_goal;
    public boolean is_border_piece;
    
    public Piece(int x,
                 int y,
                 String state,
                 String r_side,
                 String u_side,
                 String l_side,
                 String d_side,
                 boolean is_white_goal,
                 boolean is_black_goal,
                 boolean is_border_piece) {

        this.x = x;
        this.y = y;
        this.state = state;
        this.r_side = r_side;
        this.u_side = u_side;
        this.l_side = l_side;
        this.d_side = d_side;
        this.is_white_goal = is_white_goal;
        this.is_black_goal = is_black_goal;
        this.is_border_piece = is_border_piece;
    }

    public String get_position(){
        return this.x + "," + this.y;
    }

    @Override
    public String toString() {
        return "{" +
                x +
                "," + y +
                "," + state +
                "," + r_side +
                "," + u_side +
                "," + l_side +
                "," + d_side +
                "," + is_white_goal +
                "," + is_black_goal +
                "," + is_border_piece +
                '}';
    }
}
