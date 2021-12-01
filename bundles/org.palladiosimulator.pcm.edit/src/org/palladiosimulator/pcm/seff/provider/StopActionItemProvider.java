package org.palladiosimulator.pcm.seff.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.StopAction;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class StopActionItemProvider extends StopActionItemProviderGen {

    public StopActionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addSuccessor_AbstractActionPropertyDescriptor(Object object) {
        super.addSuccessor_AbstractActionPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(StopAction.class, AbstractAction.class) {
            @Override
            protected Collection<?> getValueChoiceTyped(StopAction object, List<AbstractAction> typedList) {
                return new ArrayList<AbstractAction>();
            }
        });
    }

}
