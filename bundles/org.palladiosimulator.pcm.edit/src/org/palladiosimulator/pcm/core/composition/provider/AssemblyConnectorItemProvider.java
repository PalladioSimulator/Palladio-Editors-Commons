package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.StartAction;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class AssemblyConnectorItemProvider extends AssemblyConnectorItemProviderGen {

	public AssemblyConnectorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, AssemblyContext.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<AssemblyContext> typedList) {
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	return contexts;
            }
		});
	}

	@Override
	protected void addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
	}

	@Override
	protected void addProvidedRole_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addProvidedRole_AssemblyConnectorPropertyDescriptor(object);
	}

	@Override
	protected void addRequiredRole_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addRequiredRole_AssemblyConnectorPropertyDescriptor(object);
	}
	
}
