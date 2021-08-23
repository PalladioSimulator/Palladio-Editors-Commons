package org.palladiosimulator.pcm.seff.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class ExternalCallActionItemProvider extends ExternalCallActionItemProviderGen {

	public ExternalCallActionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	protected void addSuccessor_AbstractActionPropertyDescriptor(Object object) {
		super.addSuccessor_AbstractActionPropertyDescriptor(object);
		// 1. var decoraor = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		// decorator.setValueChoiceCalculator(Anonyme Klasse new ValueChoiceDecorator(iwelche Sachen))
		// Implementiere getValueChoiceTyped() Methode, die die gefilterte Liste zurückgibt.
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);

		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(ExternalCallAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(ExternalCallAction object,
                    List<AbstractAction> typedList) {
            	ResourceDemandingBehaviour rdb = object.getResourceDemandingBehaviour_AbstractAction();
            	EList<AbstractAction> list = rdb.getSteps_Behaviour();
            	return list;
            }
		});
	}
	
}
