package org.palladiosimulator.pcm.usagemodel.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;

public class EntryLevelSystemCallItemProvider extends EntryLevelSystemCallItemProviderGen {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntryLevelSystemCallItemProvider(AdapterFactory adapterFactory)
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
	    return (((((((EntryLevelSystemCall) (object)).getEntityName() + " [ID: ") + ((EntryLevelSystemCall) (object)).getId()) + "]") + " <") + getString("_UI_EntryLevelSystemCall_type")) + ">";
	}
}
