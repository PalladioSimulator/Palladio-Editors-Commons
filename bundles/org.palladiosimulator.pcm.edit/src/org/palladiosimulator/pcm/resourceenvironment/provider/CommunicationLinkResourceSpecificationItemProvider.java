package org.palladiosimulator.pcm.resourceenvironment.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;


public class CommunicationLinkResourceSpecificationItemProvider extends CommunicationLinkResourceSpecificationItemProviderGen {

	public CommunicationLinkResourceSpecificationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
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
