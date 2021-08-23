package org.palladiosimulator.pcm.seff.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.impl.StartActionImpl;
import org.palladiosimulator.pcm.seff.impl.StopActionImpl;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

/**
 * Customized version of {@link InternalActionItemProviderGen}.
 */
public class InternalActionItemProvider extends InternalActionItemProviderGen {

    /**
     * {@inheritDoc}
     */
    public InternalActionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(Object object) {
        return (((((((InternalAction) (object)).getEntityName() + " [ID: ") + ((InternalAction) (object)).getId())
                + "]") + " <") + getString("_UI_InternalAction_type")) + ">";
    }

	@Override
	protected void addPredecessor_AbstractActionPropertyDescriptor(Object object) {
		super.addPredecessor_AbstractActionPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(InternalAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(InternalAction object,
                    List<AbstractAction> typedList) {
            	ResourceDemandingBehaviour resourceDemandingBehaviour = object.getResourceDemandingBehaviour_AbstractAction();
            	EList<AbstractAction> actionList = resourceDemandingBehaviour.getSteps_Behaviour();
            	return actionList.stream()
            			.filter(action -> !(action instanceof StopActionImpl))
            			.collect(Collectors.toList());
            }
		});
	}

	@Override
	protected void addSuccessor_AbstractActionPropertyDescriptor(Object object) {
		super.addSuccessor_AbstractActionPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(InternalAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(InternalAction object,
                    List<AbstractAction> typedList) {
            	ResourceDemandingBehaviour resourceDemandingBehaviour = object.getResourceDemandingBehaviour_AbstractAction();
            	EList<AbstractAction> actionList = resourceDemandingBehaviour.getSteps_Behaviour();
            	return actionList.stream()
            			.filter(action -> !(action instanceof StartActionImpl))
            			.collect(Collectors.toList());
            }
		});
	}
    
}
