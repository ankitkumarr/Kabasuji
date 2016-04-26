package com.halaesus.kabasuji.builder.boundary;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.PuzzleLevel;

@SuppressWarnings("serial")
public class PuzzleBuilderView extends AbstractBuilderView {

    JLabel movesLabel;
    JTextField movesBox;
    JButton setMoves;
    PuzzleLevel level;

    public PuzzleBuilderView(Application application, AbstractLevel aLevel) {
        // TODO implement here
    	super(application, aLevel);
    }
}
