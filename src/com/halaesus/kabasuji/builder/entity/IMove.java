package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;

/**
 * 
 */
public interface IMove {

    /**
     * @param AbstractLevel level
     */
    public boolean doMove(AbstractLevel level);
    
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
