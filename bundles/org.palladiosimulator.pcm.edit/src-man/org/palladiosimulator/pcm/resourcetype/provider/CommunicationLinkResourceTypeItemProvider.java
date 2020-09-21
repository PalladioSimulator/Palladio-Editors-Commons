package org.palladiosimulator.pcm.resourcetype.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourcetype.CommunicationLinkResourceType;

public class CommunicationLinkResourceTypeItemProvider extends CommunicationLinkResourceTypeItemProviderGen {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommunicationLinkResourceTypeItemProvider(AdapterFactory adapterFactory)
	{
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
	    return (((((((CommunicationLinkResourceType) (object)).getEntityName() + " [ID: ") + ((CommunicationLinkResourceType) (object)).getId()) + "]") + " <") + getString("_UI_CommunicationLinkResourceType_type")) + ">";
	}
}
