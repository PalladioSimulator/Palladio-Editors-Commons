package org.palladiosimulator.editors.commons.dialogs.resource;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.dialogs.OpenStoExDialog;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenLatencyDialog.
 */
public class OpenLatencyDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.commons.dialogs.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        // Default Implementation. Override as necessary
        if (randomVariableFeature == null && parent instanceof RandomVariable) {
            return (RandomVariable) parent;
        } else if (randomVariableFeature == null && parent instanceof CommunicationLinkResourceSpecification) {
            return ((CommunicationLinkResourceSpecification) parent)
                    .getLatency_CommunicationLinkResourceSpecification();
        } else {
            return (RandomVariable) parent.eGet(randomVariableFeature);
        }
    }

    /**
     * Gets the expected type.
     *
     * @param rv the rv
     * @return the expected type
     * @see org.palladiosimulator.editors.commons.dialogs.OpenStoExDialog#getExpectedType(de.uka.ipd.sdq.stoex.RandomVariable)
     */
    @Override
    protected TypeEnum getExpectedType(RandomVariable rv) {
        return TypeEnum.DOUBLE;
    }

}
