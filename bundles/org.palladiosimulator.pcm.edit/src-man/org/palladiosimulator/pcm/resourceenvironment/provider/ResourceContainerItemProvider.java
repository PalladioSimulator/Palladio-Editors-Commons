package org.palladiosimulator.pcm.resourceenvironment.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

public class ResourceContainerItemProvider extends ResourceContainerItemProviderGen {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceContainerItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}
	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	@Override
	public String getText(Object object)
	{
	    return (((((((ResourceContainer) (object)).getEntityName() + " [ID: ") + ((ResourceContainer) (object)).getId()) + "]") + " <") + getString("_UI_ResourceContainer_type")) + ">";
	}
}
