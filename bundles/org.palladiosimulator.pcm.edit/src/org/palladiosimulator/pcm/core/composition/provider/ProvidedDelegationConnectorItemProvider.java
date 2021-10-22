package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class ProvidedDelegationConnectorItemProvider extends ProvidedDelegationConnectorItemProviderGen{

	public ProvidedDelegationConnectorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addInnerProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(Object object) {
		super.addInnerProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(ProvidedDelegationConnector.class, OperationProvidedRole.class) {

			@Override
			protected Collection<?> getValueChoiceTyped(ProvidedDelegationConnector object,
					List<OperationProvidedRole> typedList) {
				AssemblyContext context = object.getAssemblyContext_ProvidedDelegationConnector();
				if (context != null) {
					return context.getEncapsulatedComponent__AssemblyContext().getProvidedRoles_InterfaceProvidingEntity();
				}
				if (object.getInnerProvidedRole_ProvidedDelegationConnector() == null) {
					return typedList;
				}
				Collection<AssemblyContext> contexts = object.getParentStructure__Connector().getAssemblyContexts__ComposedStructure();
            	Collection<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toList());
            	return components.stream()
            			.map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity).flatMap(List::stream).collect(Collectors.toList());
			}
			
		});
	}

	@Override
	protected void addOuterProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(Object object) {
		super.addOuterProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(ProvidedDelegationConnector.class, OperationProvidedRole.class) {

			@Override
			protected Collection<?> getValueChoiceTyped(ProvidedDelegationConnector object,
					List<OperationProvidedRole> typedList) {
				AssemblyContext context = object.getAssemblyContext_ProvidedDelegationConnector();
				if (context != null) {
					return context.getEncapsulatedComponent__AssemblyContext().getProvidedRoles_InterfaceProvidingEntity();
				}
				if (object.getInnerProvidedRole_ProvidedDelegationConnector() == null) {
					return typedList;
				}
				Collection<AssemblyContext> contexts = object.getParentStructure__Connector().getAssemblyContexts__ComposedStructure();
            	Collection<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toList());
            	return components.stream()
            			.map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity).flatMap(List::stream).collect(Collectors.toList());
			}
			
		});
	}

	@Override
	protected void addAssemblyContext_ProvidedDelegationConnectorPropertyDescriptor(Object object) {
		super.addAssemblyContext_ProvidedDelegationConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(ProvidedDelegationConnector.class, AssemblyContext.class) {

			@Override
			protected Collection<?> getValueChoiceTyped(ProvidedDelegationConnector object,
					List<AssemblyContext> typedList) {
				ComposedStructure composedStructure = object.getParentStructure__Connector();
				List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
				return contexts;
			}
			
		});
	}
	
	

}
