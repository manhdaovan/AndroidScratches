package com.manhdaovan.pluzzlegame.models;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Game {
    private Piece[] pieces;
    private int numberVertical;
    private int numberHorizontal;
    private Context context;
    private LinearLayout layout;

    public Game(int _numberVertical, int _numberHorizontal, Piece[] _pieces, LinearLayout _layout) {
        numberVertical = _numberVertical;
        numberHorizontal = _numberHorizontal;
        pieces = _pieces;
        layout = _layout;
    }

    public boolean isFinish() {
        for (Piece piece : pieces) {
            if (!piece.isCorrectOrder()) return false;
        }
        return true;
    }

    public boolean movable(Piece p1, Piece p2) { return false; }

    public void swap(Piece p1, Piece p2) {}

    public void delete() {}
}
