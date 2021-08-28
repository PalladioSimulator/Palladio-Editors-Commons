package org.palladiosimulator.pcm.seff.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.impl.StartActionImpl;
import org.palladiosimulator.pcm.seff.impl.StopActionImpl;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class ExternalCallActionItemProvider extends ExternalCallActionItemProviderGen {

	public ExternalCallActionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
//	@Override
//	protected void addSuccessor_AbstractActionPropertyDescriptor(Object object) {
//		super.addSuccessor_AbstractActionPropertyDescriptor(object);
//		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
//		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(ExternalCallAction.class, AbstractAction.class) {
//            @Override
//            protected Collection<?> getValueChoiceTyped(ExternalCallAction object,
//                    List<AbstractAction> typedList) {
//            	ResourceDemandingBehaviour resourceDemandingBehaviour = object.getResourceDemandingBehaviour_AbstractAction();
//            	EList<AbstractAction> actionList = resourceDemandingBehaviour.getSteps_Behaviour();
//            	return actionList.stream()
//            			.filter(Predicate.not(StartAction.class::isInstance))
//            			.collect(Collectors.toList());
//            }
//		});
//	}
//
//	@Override
//	protected void addPredecessor_AbstractActionPropertyDescriptor(Object object) {
//		super.addPredecessor_AbstractActionPropertyDescriptor(object);
//		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
//		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(ExternalCallAction.class, AbstractAction.class) {
//            @Override
//            protected Collection<?> getValueChoiceTyped(ExternalCallAction object,
//                    List<AbstractAction> typedList) {
//            	ResourceDemandingBehaviour resourceDemandingBehaviour = object.getResourceDemandingBehaviour_AbstractAction();
//            	EList<AbstractAction> actionList = resourceDemandingBehaviour.getSteps_Behaviour();
//            	return actionList.stream()
//            			.filter(action -> !(action instanceof StopActionImpl))
//            			.collect(Collectors.toList());
//            }
//		});
//	}
	
}