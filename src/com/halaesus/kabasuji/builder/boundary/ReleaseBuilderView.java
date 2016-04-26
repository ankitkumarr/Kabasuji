package com.halaesus.kabasuji.builder.boundary;

import java.awt.image.BufferedImage;

import com.halaesus.kabasuji.shared.entity.AbstractLevel;
import com.halaesus.kabasuji.shared.entity.ReleaseLevel;

@SuppressWarnings("serial")
public class ReleaseBuilderView extends AbstractBuilderView {
    BufferedImage[] numberBarNumbers;
    ReleaseLevel level;

    public ReleaseBuilderView(Application application, AbstractLevel aLevel) {
        // TODO implement here
    	super(application, aLevel);
    }
}
