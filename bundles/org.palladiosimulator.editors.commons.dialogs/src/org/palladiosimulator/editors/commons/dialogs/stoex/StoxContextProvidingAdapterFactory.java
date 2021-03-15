package org.palladiosimulator.editors.commons.dialogs.stoex;

import org.palladiosimulator.editors.commons.dialogs.stoex.impl.StoexContextProvidingAdapterImpl;

public interface StoxContextProvidingAdapterFactory {

    public static StoExContextProvidingAdapter create() {
        return new StoexContextProvidingAdapterImpl();
    }
    
}
