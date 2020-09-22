package org.palladiosimulator.pcm.repository.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;

/**
 * Customized version of {@link BasicComponentItemProviderGen}.
 */
public class BasicComponentItemProvider extends BasicComponentItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public BasicComponentItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((BasicComponent) (object)).getEntityName() + " [ID: ") + ((BasicComponent) (object)).getId())
                + "]") + " <") + getString("_UI_BasicComponent_type")) + ">";
    }

}
