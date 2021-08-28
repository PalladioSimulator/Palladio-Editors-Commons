package org.palladiosimulator.pcm.seff.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.impl.StartActionImpl;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class StartActionItemProvider extends StartActionItemProviderGen{

	public StartActionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addPredecessor_AbstractActionPropertyDescriptor(Object object) {
		super.addPredecessor_AbstractActionPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(StartAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(StartAction object,
                    List<AbstractAction> typedList) {
            	return new ArrayList<AbstractAction>();
            }
		});
	}

}
