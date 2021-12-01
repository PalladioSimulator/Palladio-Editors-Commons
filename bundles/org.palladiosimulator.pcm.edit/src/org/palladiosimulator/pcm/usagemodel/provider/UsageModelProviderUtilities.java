package org.palladiosimulator.pcm.usagemodel.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class UsageModelProviderUtilities {

	protected static ValueChoiceCalculatorBase createEmptyValueChoiceCalculator() {
		return new ValueChoiceCalculatorBase<>(AbstractUserAction.class, AbstractUserAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(AbstractUserAction object, List<AbstractUserAction> typedList) {
                return new ArrayList<AbstractUserAction>();
            }
        };
	}
}
