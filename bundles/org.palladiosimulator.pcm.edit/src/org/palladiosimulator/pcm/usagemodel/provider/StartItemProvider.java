package org.palladiosimulator.pcm.usagemodel.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.Start;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class StartItemProvider extends StartItemProviderGen {

	public StartItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addPredecessorPropertyDescriptor(Object object) {
		super.addPredecessorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(Start.class, AbstractUserAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(Start object,
                    List<AbstractUserAction> typedList) {
            	return new ArrayList<AbstractUserAction>();
            }
		});
	}

	
}
