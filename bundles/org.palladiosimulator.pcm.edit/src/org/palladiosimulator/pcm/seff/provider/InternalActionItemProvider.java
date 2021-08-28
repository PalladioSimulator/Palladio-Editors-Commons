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
    
}
