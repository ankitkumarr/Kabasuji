package com.halaesus.kabasuji.builder.entity;


import java.util.*;

import com.halaesus.kabasuji.shared.AbstractLevel;
import com.halaesus.kabasuji.shared.IMove;

/**
 * 
 */
public class MoveManager {

    public MoveManager() {
    }

    Stack<IMove> moves;
    Stack<IMove> undoneMoves;
    AbstractLevel level;

}