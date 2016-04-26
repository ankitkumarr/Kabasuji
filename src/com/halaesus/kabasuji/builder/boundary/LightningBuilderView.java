package com.halaesus.kabasuji.builder.boundary;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.LightningLevel;

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
