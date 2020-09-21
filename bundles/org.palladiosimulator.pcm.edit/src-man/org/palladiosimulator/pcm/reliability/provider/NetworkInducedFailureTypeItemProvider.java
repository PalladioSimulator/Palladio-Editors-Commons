package org.palladiosimulator.pcm.reliability.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.reliability.NetworkInducedFailureType;


public class NetworkInducedFailureTypeItemProvider extends NetworkInducedFailureTypeItemProviderGen {

    public NetworkInducedFailureTypeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> FB: Adapted method
     * to produce a more informative label for display in non-PCM editors. In PCM editors, display
     * is not controlled by this method but through a more general mechanism (I'n not sure where
     * it's actually controlled, maybe the
     * org.palladiosimulator.editors.commons.tabs.PropertyLabelProvider.getText() method?) <!--
     * end-user-doc -->
     *
     * @generated not
     */
    @Override
    public String getText(final Object object) {
        return (((((((NetworkInducedFailureType) (object)).getEntityName() + " [ID: ") + ((NetworkInducedFailureType) (object)).getId()) + "]") + " <") + getString("_UI_NetworkInducedFailureType_type")) + ">";
    }
}
