package com.halaesus.kabasuji.builder.entity;


import java.util.*;


public class ReleaseBoard extends AbstractBoard {

   
    Set<ReleaseNumber> numbers;
    
    public ReleaseBoard(BoardSquare[] boardSquares) {
        super(boardSquares);
    }

}