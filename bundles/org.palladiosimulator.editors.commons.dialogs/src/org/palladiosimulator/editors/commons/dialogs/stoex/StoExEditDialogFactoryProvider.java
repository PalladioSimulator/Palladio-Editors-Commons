package org.palladiosimulator.editors.commons.dialogs.stoex;

import org.palladiosimulator.editors.commons.dialogs.stoex.impl.StoExEditDialogFactoryImpl;
import org.palladiosimulator.pcm.stoex.ui.internal.StoexActivator;

public final class StoExEditDialogFactoryProvider {

    private static final StoExEditDialogFactory FACTORY = createInstance();

    private StoExEditDialogFactoryProvider() {
        // intentionally left blank
    }

    public static StoExEditDialogFactory getInstance() {
        return FACTORY;
    }

    private static StoExEditDialogFactory createInstance() {
        var injector = StoexActivator.getInstance()
            .getInjector(StoexActivator.ORG_PALLADIOSIMULATOR_PCM_STOEX_PCMSTOEX);
        return injector.getInstance(StoExEditDialogFactoryImpl.class);
    }
}
