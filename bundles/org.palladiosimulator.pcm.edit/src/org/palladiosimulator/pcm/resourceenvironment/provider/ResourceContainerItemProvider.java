package org.palladiosimulator.pcm.resourceenvironment.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

/**
 * Customized version of {@link ResourceContainerItemProviderGen}.
 */
public class ResourceContainerItemProvider extends ResourceContainerItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public ResourceContainerItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((ResourceContainer) (object)).getEntityName() + " [ID: ") + ((ResourceContainer) (object)).getId())
                + "]") + " <") + getString("_UI_ResourceContainer_type")) + ">";
    }
}
