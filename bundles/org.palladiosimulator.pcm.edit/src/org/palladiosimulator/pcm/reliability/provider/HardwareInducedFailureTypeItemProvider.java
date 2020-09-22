package org.palladiosimulator.pcm.reliability.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.reliability.HardwareInducedFailureType;

/**
 * Customized version of {@link HardwareInducedFailureTypeItemProviderGen}.
 */
public class HardwareInducedFailureTypeItemProvider extends HardwareInducedFailureTypeItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public HardwareInducedFailureTypeItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((HardwareInducedFailureType) (object)).getEntityName() + " [ID: ")
                + ((HardwareInducedFailureType) (object)).getId()) + "]") + " <")
                + getString("_UI_HardwareInducedFailureType_type")) + ">";
    }
}
