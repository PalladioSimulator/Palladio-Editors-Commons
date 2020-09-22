package org.palladiosimulator.pcm.usagemodel.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;

/**
 * Customized version of {@link EntryLevelSystemCallItemProviderGen}.
 */
public class EntryLevelSystemCallItemProvider extends EntryLevelSystemCallItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public EntryLevelSystemCallItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((EntryLevelSystemCall) (object)).getEntityName() + " [ID: ")
                + ((EntryLevelSystemCall) (object)).getId()) + "]") + " <")
                + getString("_UI_EntryLevelSystemCall_type")) + ">";
    }
}
