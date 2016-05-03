package com.halaesus.kabasuji.builder.entity;

import com.halaesus.kabasuji.builder.boundary.AbstractBuilderView;
import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.PieceSquare;

public class ToggleHintMove extends PieceMove {

	public ToggleHintMove(AbstractLevel theLevel, AbstractBuilderView theBuilderView) {
		super(theLevel, theBuilderView);
	}

	@Override
	public boolean isValid() {
		return true; // One can always toggle a hint
	}

	@Override
	public boolean doMove() {
		if( isValid() == false )
			return false; // We couldn't perform the move as it is not valid in the first place
		if( getOriginalPiece() == null )
			return false; // We cannot perform the move as the piece for which its BoardSquares are to be updated are not available :(
		// We can now perform the move
		for( PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares() )
			if( this.theLevel.getBoard().getHint(aPieceSquare.getRow(), aPieceSquare.getCol()) == (-1) )
				this.theLevel.getBoard().addHint(aPieceSquare.getRow(), aPieceSquare.getCol(), getOriginalPiece().getColorID()); // Add the hint
			else
				this.theLevel.getBoard().addHint(aPieceSquare.getRow(), aPieceSquare.getCol(), -1); // Remove the hint
		// The final piece hasn't changed so:
		setFinalPiece(getOriginalPiece());
		// As the move was successful:
		return true;
	}

	@Override
	public boolean undoMove() {
		// Perform the undo
		for( PieceSquare aPieceSquare : getFinalPiece().getPieceSquares() )
			if( this.theLevel.getBoard().getHint(aPieceSquare.getRow(), aPieceSquare.getCol()) == (-1) )
				this.theLevel.getBoard().addHint(aPieceSquare.getRow(), aPieceSquare.getCol(), getFinalPiece().getColorID()); // Add the hint
			else
				this.theLevel.getBoard().addHint(aPieceSquare.getRow(), aPieceSquare.getCol(), -1); // Remove the hint
		// As the undo was successful:
		return true;
	}

	@Override
	public boolean redoMove() {
		// Perform the redo
		for( PieceSquare aPieceSquare : getOriginalPiece().getPieceSquares() )
			if( this.theLevel.getBoard().getHint(aPieceSquare.getRow(), aPieceSquare.getCol()) == (-1) )
				this.theLevel.getBoard().addHint(aPieceSquare.getRow(), aPieceSquare.getCol(), getOriginalPiece().getColorID()); // Add the hint
			else
				this.theLevel.getBoard().addHint(aPieceSquare.getRow(), aPieceSquare.getCol(), -1); // Remove the hint
		// As the redo was successful:
		return true;
	}

}
