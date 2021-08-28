package org.palladiosimulator.pcm.usagemodel.provider;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.Start;
import org.palladiosimulator.pcm.usagemodel.Stop;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

/**
 * @author Nathan
 *
 */
public class AbstractUserActionItemProvider extends AbstractUserActionItemProviderGen{

	public AbstractUserActionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addSuccessorPropertyDescriptor(Object object) {
		super.addSuccessorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AbstractUserAction.class, AbstractUserAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(AbstractUserAction object,
                    List<AbstractUserAction> typedList) {
            	ScenarioBehaviour resourceDemandingBehaviour = object.getScenarioBehaviour_AbstractUserAction();
            	EList<AbstractUserAction> actionList = resourceDemandingBehaviour.getActions_ScenarioBehaviour();
            	return actionList.stream()
            			.filter(Predicate.not(Start.class::isInstance))
            			.filter(a -> object != a)
            			.collect(Collectors.toList());
            }
		});
	}

	@Override
	protected void addPredecessorPropertyDescriptor(Object object) {
		super.addPredecessorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AbstractUserAction.class, AbstractUserAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(AbstractUserAction object,
                    List<AbstractUserAction> typedList) {
            	ScenarioBehaviour resourceDemandingBehaviour = object.getScenarioBehaviour_AbstractUserAction();
            	EList<AbstractUserAction> actionList = resourceDemandingBehaviour.getActions_ScenarioBehaviour();
            	return actionList.stream()
            			.filter(Predicate.not(Stop.class::isInstance))
            			.filter(a -> object != a)
            			.collect(Collectors.toList());
            }
		});
	}

	
}
