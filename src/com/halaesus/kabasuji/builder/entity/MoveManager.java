package com.halaesus.kabasuji.builder.entity;


import java.util.*;

import com.halaesus.kabasuji.player.entity.IMove;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;

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