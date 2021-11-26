package org.palladiosimulator.pcm.seff.provider;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.StopAction;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class AbstractActionItemProvider extends AbstractActionItemProviderGen {

	public AbstractActionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	protected void addSuccessor_AbstractActionPropertyDescriptor(Object object) {
		super.addSuccessor_AbstractActionPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AbstractAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(AbstractAction object,
                    List<AbstractAction> typedList) {
            	ResourceDemandingBehaviour resourceDemandingBehaviour = object.getResourceDemandingBehaviour_AbstractAction();
            	EList<AbstractAction> actionList = resourceDemandingBehaviour.getSteps_Behaviour();
            	return actionList.stream()
            			.filter(Predicate.not(StartAction.class::isInstance)) //EMF Package verwenden.
            			.filter(a -> object != a)
            			.collect(Collectors.toList());
            }
		});
	}

	@Override
	protected void addPredecessor_AbstractActionPropertyDescriptor(Object object) {
		super.addPredecessor_AbstractActionPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AbstractAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(AbstractAction object,
                    List<AbstractAction> typedList) {
            	ResourceDemandingBehaviour resourceDemandingBehaviour = object.getResourceDemandingBehaviour_AbstractAction();
            	EList<AbstractAction> actionList = resourceDemandingBehaviour.getSteps_Behaviour();
            	return actionList.stream()
            			.filter(Predicate.not(StopAction.class::isInstance))
            			.filter(a -> object != a)
            			.collect(Collectors.toList());
            }
		});
	}
	
}
