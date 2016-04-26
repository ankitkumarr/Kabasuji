package com.halaesus.kabasuji.builder.boundary;

import com.halaesus.kabasuji.builder.entity.*;
import com.halaesus.kabasuji.shared.AbstractLevel;
import com.halaesus.kabasuji.shared.PuzzleLevel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
