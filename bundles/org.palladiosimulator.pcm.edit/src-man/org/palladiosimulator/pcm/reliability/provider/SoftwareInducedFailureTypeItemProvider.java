package org.palladiosimulator.pcm.reliability.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;

public class SoftwareInducedFailureTypeItemProvider extends SoftwareInducedFailureTypeItemProviderGen {

	
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	public SoftwareInducedFailureTypeItemProvider(AdapterFactory adapterFactory)
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
	    return (((((((SoftwareInducedFailureType) (object)).getEntityName() + " [ID: ") + ((SoftwareInducedFailureType) (object)).getId()) + "]") + " <") + getString("_UI_SoftwareInducedFailureType_type")) + ">";
	}
}
