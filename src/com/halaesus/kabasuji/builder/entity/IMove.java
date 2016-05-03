package com.halaesus.kabasuji.builder.entity;

/**
 * @author asoota Akshit (Axe) Soota (axe (at) wpi (dot) edu)
 */
public interface IMove {
    public boolean doMove();
    public boolean isValid();
    public boolean undoMove();
    public boolean redoMove();
}
