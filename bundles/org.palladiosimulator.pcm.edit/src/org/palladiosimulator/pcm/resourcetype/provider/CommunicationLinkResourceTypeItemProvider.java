package org.palladiosimulator.pcm.resourcetype.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourcetype.CommunicationLinkResourceType;

/**
 * Customized version of {@link CommunicationLinkResourceTypeItemProviderGen}.
 */
public class CommunicationLinkResourceTypeItemProvider extends CommunicationLinkResourceTypeItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public CommunicationLinkResourceTypeItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((CommunicationLinkResourceType) (object)).getEntityName() + " [ID: ")
                + ((CommunicationLinkResourceType) (object)).getId()) + "]") + " <")
                + getString("_UI_CommunicationLinkResourceType_type")) + ">";
    }
}
