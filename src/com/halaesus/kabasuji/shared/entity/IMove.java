package com.halaesus.kabasuji.shared.entity;

public interface IMove {

    public boolean isValid(AbstractLevel level);
    public Piece doMove(AbstractLevel level, PieceSquare[] originalPieceSquares);

}