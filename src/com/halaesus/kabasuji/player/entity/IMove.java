package com.halaesus.kabasuji.player.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

public interface IMove {

    public boolean isValid(AbstractLevel level);
    public Piece doMove(AbstractLevel level, PieceSquare[] originalPieceSquares);

}