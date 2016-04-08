package com.halaesus.kabasuji.builder.entity;


import java.util.*;

/**
 * 
 */
public class MoveManager {

    /**
     * Default constructor
     */
    public MoveManager() {
    }

    /**
     * 
     */
    Stack<IMove> moves;

    /**
     * 
     */
    Stack<IMove> undoneMoves;

    /**
     * 
     */
    AbstractLevel level;

}