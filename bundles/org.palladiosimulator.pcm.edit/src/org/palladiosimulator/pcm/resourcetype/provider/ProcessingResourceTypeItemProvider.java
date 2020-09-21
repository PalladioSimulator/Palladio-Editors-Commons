package org.palladiosimulator.pcm.resourcetype.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.resourcetype.ProcessingResourceType;

public class ProcessingResourceTypeItemProvider extends ProcessingResourceTypeItemProviderGen{

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessingResourceTypeItemProvider(AdapterFactory adapterFactory)
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
	    return (((((((ProcessingResourceType) (object)).getEntityName() + " [ID: ") + ((ProcessingResourceType) (object)).getId()) + "]") + " <") + getString("_UI_ProcessingResourceType_type")) + ">";
	}
	
}
