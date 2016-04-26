package com.halaesus.kabasuji.builder.boundary;

import com.halaesus.kabasuji.builder.entity.*;
import com.halaesus.kabasuji.shared.AbstractLevel;
import com.halaesus.kabasuji.shared.ReleaseLevel;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class ReleaseBuilderView extends AbstractBuilderView {
    BufferedImage[] numberBarNumbers;
    ReleaseLevel level;

    public ReleaseBuilderView(Application application, AbstractLevel aLevel) {
        // TODO implement here
    	super(application, aLevel);
    }
}
