package org.palladiosimulator.editors.commons.dialogs.stoex;

import de.uka.ipd.sdq.stoex.Expression;

public interface StoExEditDialog {

    int open();

    int getReturnCode();

    String getResultString();

    Expression getResult();

    void setInitialExpression(Expression expression);

    void setDisplayTitle(String dialogMessage);

}
