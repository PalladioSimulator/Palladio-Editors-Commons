package org.palladiosimulator.editors.commons.dialogs.stoex;

import org.eclipse.emf.common.notify.Adapter;
import org.palladiosimulator.commons.stoex.services.StoexContextProvider;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public interface StoExContextProvidingAdapter extends Adapter, StoexContextProvider {
    void setStoexContainer(RandomVariable stoexContainer);
    void setExpectedType(TypeEnum expectedType);
}
