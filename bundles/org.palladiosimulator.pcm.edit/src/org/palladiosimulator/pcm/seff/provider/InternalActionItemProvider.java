package org.palladiosimulator.pcm.seff.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.seff.InternalAction;

/**
 * Customized version of {@link InternalActionItemProviderGen}.
 */
public class InternalActionItemProvider extends InternalActionItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public InternalActionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((InternalAction) (object)).getEntityName() + " [ID: ") + ((InternalAction) (object)).getId())
                + "]") + " <") + getString("_UI_InternalAction_type")) + ">";
    }
}
