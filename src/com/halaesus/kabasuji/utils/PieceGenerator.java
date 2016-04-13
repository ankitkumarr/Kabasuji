package com.halaesus.kabasuji.utils;

import java.awt.Color;

import com.halaesus.kabasuji.player.entity.Piece;
import com.halaesus.kabasuji.player.entity.PieceSquare;

public class PieceGenerator {

	//Note: Only correctly defined the first 18 hexominoes
	private static PieceSquare pieceSquares[][] = new PieceSquare[][]{
		{	// 1
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(0, 4),
			new PieceSquare(0, 5),
		},
		{	// 2
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(0, 4),
			new PieceSquare(1, 0),
		},
		{	// 3
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(0, 4),
			new PieceSquare(1, 1),
		},
		{	// 4
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(0, 4),
			new PieceSquare(1, 2),
		},
		{	// 5
			new PieceSquare(1, 0),
			new PieceSquare(1, 1),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(0, 4),
		},
		{	// 6
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(1, 0),
			new PieceSquare(1, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
		},
		{	// 7
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(2, 2),
			new PieceSquare(0, 3),
		},
		{	// 8
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(1, 3),
		},
		{	// 9
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
		},
		{	// 10
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
		},
		{	// 11
			new PieceSquare(0, 0),
			new PieceSquare(0, 1),
			new PieceSquare(0, 2),
			new PieceSquare(0, 3),
			new PieceSquare(1, 1),
			new PieceSquare(2, 1),
		},
		{	// 12
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 13
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(0, 1),
			new PieceSquare(1, 3),
		},
		{	// 14
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(0, 2),
			new PieceSquare(1, 3),
		},
		{	// 15
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(0, 3),
			new PieceSquare(1, 3),
		},
		{	// 16
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 2),
			new PieceSquare(1, 2),
			new PieceSquare(0, 2),
			new PieceSquare(1, 3),
		},
		{	// 17
			new PieceSquare(1, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
			new PieceSquare(0, 1),
			new PieceSquare(2, 1),
		},
		{	// 18 // 
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 19
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 20
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 21
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 22
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 23
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 24
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 25
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 26
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 27
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 28
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 29
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 30
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 31
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 32
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 33
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 34
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		},
		{	// 35
			new PieceSquare(0, 0),
			new PieceSquare(1, 0),
			new PieceSquare(2, 0),
			new PieceSquare(1, 1),
			new PieceSquare(1, 2),
			new PieceSquare(1, 3),
		}	
	};
	

	public static final Piece pieces[] = new Piece[]{
		new Piece(0, 0, new Color(250, 2, 2), pieceSquares[0]),
		new Piece(0, 0, new Color(36, 217, 18), pieceSquares[1]),
		new Piece(0, 0, new Color(67, 60, 166), pieceSquares[2]),
		new Piece(0, 0, new Color(101, 255, 0), pieceSquares[3]),
		new Piece(0, 0, new Color(229, 68, 236), pieceSquares[4]),
		new Piece(0, 0, new Color(255, 39, 212), pieceSquares[5]),
		new Piece(0, 0, new Color(255, 173, 0), pieceSquares[6]),
		new Piece(0, 0, new Color(254, 19, 0), pieceSquares[7]),
		new Piece(0, 0, new Color(218, 112, 26), pieceSquares[8]),
		new Piece(0, 0, new Color(1, 42, 255), pieceSquares[9]),
		new Piece(0, 0, new Color(138, 58, 31), pieceSquares[10]),
		new Piece(0, 0, new Color(193, 53, 188), pieceSquares[11]),
		new Piece(0, 0, new Color(255, 199, 78), pieceSquares[12]),
		new Piece(0, 0, new Color(170, 170, 220), pieceSquares[13]),
		new Piece(0, 0, new Color(241, 202, 47), pieceSquares[14]),
		new Piece(0, 0, new Color(55, 144, 54), pieceSquares[15]),
		new Piece(0, 0, new Color(59, 211, 212), pieceSquares[16]),
		new Piece(0, 0, new Color(143, 20, 173), pieceSquares[17]),
		new Piece(0, 0, new Color(71, 115, 56), pieceSquares[18]),
		new Piece(0, 0, new Color(212, 59, 199), pieceSquares[19]),
		new Piece(0, 0, new Color(130, 177, 37), pieceSquares[20]),
		new Piece(0, 0, new Color(135, 57, 35), pieceSquares[21]),
		new Piece(0, 0, new Color(20, 173, 49), pieceSquares[22]),
		new Piece(0, 0, new Color(170, 170, 222), pieceSquares[23]),
		new Piece(0, 0, new Color(127, 0, 178), pieceSquares[24]),
		new Piece(0, 0, new Color(57, 199, 53), pieceSquares[25]),
		new Piece(0, 0, new Color(130, 33, 192), pieceSquares[26]),
		new Piece(0, 0, new Color(170, 170, 170), pieceSquares[27]),
		new Piece(0, 0, new Color(254, 250, 71), pieceSquares[28]),
		new Piece(0, 0, new Color(255, 139, 70), pieceSquares[29]),
		new Piece(0, 0, new Color(171, 221, 170), pieceSquares[30]),
		new Piece(0, 0, new Color(255, 162, 0), pieceSquares[31]),
		new Piece(0, 0, new Color(43, 44, 188), pieceSquares[32]),
		new Piece(0, 0, new Color(230, 230, 230), pieceSquares[33]),
		new Piece(0, 0, new Color(189, 23, 23), pieceSquares[34])	
	};
	
}