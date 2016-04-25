package com.halaesus.kabasuji.builder.boundary;

import com.halaesus.kabasuji.builder.entity.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LightningBuilderView extends AbstractBuilderView {
    JLabel timeRemLabel;
    JTextField minText;
    JTextField secText;
    JLabel minLabel;
    JLabel secLabel;
    JLabel randPiecesLabel;
    JTextField randPiecesText;
    JButton setTime;
    JButton setPiecesCount;
    LightningLevel level;

    public LightningBuilderView(Application application, AbstractLevel aLevel) {
        // TODO implement here
    	super(application, aLevel);
    }
}
