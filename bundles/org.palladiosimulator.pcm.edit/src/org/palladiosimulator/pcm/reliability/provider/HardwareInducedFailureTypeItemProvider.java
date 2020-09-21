package org.palladiosimulator.pcm.reliability.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.reliability.HardwareInducedFailureType;

public class HardwareInducedFailureTypeItemProvider extends HardwareInducedFailureTypeItemProviderGen {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HardwareInducedFailureTypeItemProvider(AdapterFactory adapterFactory)
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
			return (((((((HardwareInducedFailureType) (object)).getEntityName() +
			" [ID: ") + ((HardwareInducedFailureType) (object)).getId()) + "]") + " <") + 
			getString("_UI_HardwareInducedFailureType_type")) + ">";
	}
}
