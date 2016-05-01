package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.Piece;

/**
 * 
 */
public interface IMove {

    /**
     * @param AbstractLevel level
     */
    public Piece doMove(AbstractLevel level);
    
    /**
     * @param AbstractLevel level
     */
    public boolean undoMove(AbstractLevel level);
    
    /**
     * @param AbstractLevel level
     */
    public boolean redoMove(AbstractLevel level);

    /**
     * @param AbstractLevel level
     */
    public boolean isValid(AbstractLevel level);
}
