package org.palladiosimulator.pcm.repository.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.repository.BasicComponent;

public class BasicComponentItemProvider extends BasicComponentItemProviderGen{

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BasicComponentItemProvider(AdapterFactory adapterFactory)
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
	    return (((((((BasicComponent) (object)).getEntityName() + " [ID: ") + ((BasicComponent) (object)).getId()) + "]") + " <") + getString("_UI_BasicComponent_type")) + ">";
	}

}
