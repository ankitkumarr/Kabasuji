package com.halaesus.kabasuji.player.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;

public interface IMove {

    public boolean isValid(AbstractLevel level);
    public Piece doMove(AbstractLevel level, PieceSquare[] originalPieceSquares);

}