package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Role;

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
				Role myRole = object.getProvidedRole_AssemblyConnector();
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	return contexts;
            }
		});
	}

	@Override
	protected void addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, AssemblyContext.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<AssemblyContext> typedList) {
				Role myRole = object.getRequiredRole_AssemblyConnector();
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	return contexts;
            }
		});
	}

	@Override
	protected void addProvidedRole_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addProvidedRole_AssemblyConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, OperationProvidedRole.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<OperationProvidedRole> typedList) {
				OperationInterface myRole = object.getRequiredRole_AssemblyConnector().getRequiredInterface__OperationRequiredRole();
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	List<RepositoryComponent> basicComponents = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toList());
            	//Wie bekomme ich hier jetzt die OperationInterfaces der basicComponents?
            	return contexts;
            }
		});
	}

	@Override
	protected void addRequiredRole_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addRequiredRole_AssemblyConnectorPropertyDescriptor(object);
	}

}
