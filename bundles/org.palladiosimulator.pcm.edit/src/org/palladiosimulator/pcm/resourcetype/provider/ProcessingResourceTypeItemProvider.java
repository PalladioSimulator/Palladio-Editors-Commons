package org.palladiosimulator.pcm.resourcetype.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;

/**
 * Customized version of {@link ProcessingResourceTypeItemProviderGen}.
 */
public class ProcessingResourceTypeItemProvider extends ProcessingResourceTypeItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public ProcessingResourceTypeItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((ProcessingResourceType) (object)).getEntityName() + " [ID: ")
                + ((ProcessingResourceType) (object)).getId()) + "]") + " <")
                + getString("_UI_ProcessingResourceType_type")) + ">";
    }

}
