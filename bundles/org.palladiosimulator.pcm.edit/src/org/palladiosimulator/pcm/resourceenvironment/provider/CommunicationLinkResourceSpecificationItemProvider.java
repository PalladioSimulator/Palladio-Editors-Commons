package org.palladiosimulator.pcm.resourceenvironment.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;

/**
 * Customized version of {@link CommunicationLinkResourceSpecificationItemProviderGen}.
 */
public class CommunicationLinkResourceSpecificationItemProvider extends CommunicationLinkResourceSpecificationItemProviderGen {

    /**
     * {@inheritDoc}
     */
	public CommunicationLinkResourceSpecificationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public String getText(Object object)
	{
	    String linkingResourceName = "UNSPECIFIED";
	    try {
	        linkingResourceName = ((CommunicationLinkResourceSpecification) (object)).getLinkingResource_CommunicationLinkResourceSpecification().getEntityName();
	    } catch (Exception e) {
	    }
	    return (((((((("ResourceSpecification" + " (") + linkingResourceName) + ")") + " [ID: ") + ((CommunicationLinkResourceSpecification) (object)).getId()) + "]") + " <") + getString("_UI_CommunicationLinkResourceSpecification_type")) + ">";
	}
	
}
