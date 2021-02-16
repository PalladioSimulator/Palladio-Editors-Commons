package org.palladiosimulator.editors.commons.dialogs.stoex;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.stoex.ui.dialog.StoExEditDialog;
import org.palladiosimulator.pcm.stoex.ui.dialog.StoExEditDialogFactory;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class StochasticExpressionEditDialog {

    protected final StoExEditDialog dialog;

    public StochasticExpressionEditDialog(Shell shell, TypeEnum expectedType) {
        this(shell, expectedType, null);
    }

    public StochasticExpressionEditDialog(Shell shell, TypeEnum expectedType, EObject elementToConfigure) {
        this.dialog = StoExEditDialogFactory.getInstance()
            .create(shell, expectedType);
        // TODO use passed element to improve completion proposals
    }

    public int open() {
        return dialog.open();
    }

    public int getReturnCode() {
        return dialog.getReturnCode();
    }

    public String getResultText() {
        return dialog.getResultString();
    }

    public EObject getResult() {
        return dialog.getResult();
    }

    public void setInitialExpression(RandomVariable randVar) {
        dialog.setInitialExpression(randVar.getExpression());
    }

    public void setDisplayTitle(String dialogMessage) {
        dialog.setDisplayTitle(dialogMessage);
    }

    /**
     * Get the type of a variable characterisation.
     * 
     * @param ch
     *            The characterisation to check.
     * @return INT for ByteSize and number of elements, ANY for all others.
     */
    public static TypeEnum getTypeFromVariableCharacterisation(final VariableCharacterisation ch) {
        switch (ch.getType()) {
        case BYTESIZE:
        case NUMBER_OF_ELEMENTS:
            return TypeEnum.INT;
        default:
            // unspecified
        }
        return TypeEnum.ANY;
    }

}
