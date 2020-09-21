package org.palladiosimulator.pcm.seff.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.seff.InternalAction;

public class InternalActionItemProvider extends InternalActionItemProviderGen {

	public InternalActionItemProvider(AdapterFactory adapterFactory) {
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
	    return (((((((InternalAction) (object)).getEntityName() + " [ID: ") + ((InternalAction) (object)).getId()) + "]") + " <") + getString("_UI_InternalAction_type")) + ">";
	}
}
