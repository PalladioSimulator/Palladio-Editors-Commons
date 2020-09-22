package org.palladiosimulator.pcm.reliability.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;

/**
 * Customized version of {@link SoftwareInducedFailureTypeItemProviderGen}.
 */
public class SoftwareInducedFailureTypeItemProvider extends SoftwareInducedFailureTypeItemProviderGen {

    /**
     * {@inheritDoc}
     */
	public SoftwareInducedFailureTypeItemProvider(AdapterFactory adapterFactory)
	{
		super(adapterFactory);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((SoftwareInducedFailureType) (object)).getEntityName() + " [ID: ")
                + ((SoftwareInducedFailureType) (object)).getId()) + "]") + " <")
                + getString("_UI_SoftwareInducedFailureType_type")) + ">";
    }
}
