package org.palladiosimulator.pcm.seff.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.StartAction;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class SeffItemProviderUtilities {
	protected static ValueChoiceCalculatorBase createEmptyValueChoiceCalculator() {
		return new ValueChoiceCalculatorBase<>(AbstractAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(AbstractAction object, List<AbstractAction> typedList) {
                return new ArrayList<AbstractAction>();
            }
        };
	}
}
