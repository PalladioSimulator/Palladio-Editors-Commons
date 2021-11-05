package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class RequiredDelegationConnectorItemProvider extends RequiredDelegationConnectorItemProviderGen{

	public RequiredDelegationConnectorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected void addInnerRequiredRole_RequiredDelegationConnectorPropertyDescriptor(Object object) {
		super.addInnerRequiredRole_RequiredDelegationConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(RequiredDelegationConnector.class, OperationRequiredRole.class) {

			@Override
			protected Collection<?> getValueChoiceTyped(RequiredDelegationConnector object,
					List<OperationRequiredRole> typedList) {
				ComposedStructure composedStructure = object.getParentStructure__Connector();
				Collection<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
				Collection<OperationRequiredRole> operationRequiredRoles = contexts.stream()
            			.map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
            			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity)
            			.flatMap(List::stream).map(OperationRequiredRole.class::cast)
            			.collect(Collectors.toSet());
				
				AssemblyContext context = object.getAssemblyContext_RequiredDelegationConnector();
				if (context != null) {
					Collection<OperationRequiredRole> contextsRoles = context.getEncapsulatedComponent__AssemblyContext()
							.getRequiredRoles_InterfaceRequiringEntity()
							.stream()
							.filter(OperationRequiredRole.class::isInstance)
							.map(OperationRequiredRole.class::cast)
							.collect(Collectors.toList());
					operationRequiredRoles = contextsRoles;
				}
				OperationRequiredRole outerRole = object.getOuterRequiredRole_RequiredDelegationConnector();
				if (outerRole != null) {
					operationRequiredRoles = operationRequiredRoles.stream()
					.filter(opr -> opr.getRequiredInterface__OperationRequiredRole().isAssignableFrom(outerRole.getRequiredInterface__OperationRequiredRole()))
					.collect(Collectors.toList());
				}
				return operationRequiredRoles;
			}
		});
	}

	@Override
	protected void addOuterRequiredRole_RequiredDelegationConnectorPropertyDescriptor(Object object) {
		super.addOuterRequiredRole_RequiredDelegationConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(RequiredDelegationConnector.class, OperationRequiredRole.class) {

			@Override
			protected Collection<?> getValueChoiceTyped(RequiredDelegationConnector object,
					List<OperationRequiredRole> typedList) {
				AssemblyContext context = object.getAssemblyContext_RequiredDelegationConnector();
				if (context != null) {
					return context.getEncapsulatedComponent__AssemblyContext().getRequiredRoles_InterfaceRequiringEntity();
				}
				if (object.getInnerRequiredRole_RequiredDelegationConnector() == null) {
					return typedList;
				}
				Collection<AssemblyContext> contexts = object.getParentStructure__Connector().getAssemblyContexts__ComposedStructure();
            	Collection<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toList());
            	return components.stream()
            			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity).flatMap(List::stream).collect(Collectors.toList());
			}
		});
	}

	@Override
	protected void addAssemblyContext_RequiredDelegationConnectorPropertyDescriptor(Object object) {
		super.addAssemblyContext_RequiredDelegationConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(RequiredDelegationConnector.class, AssemblyContext.class) {

			@Override
			protected Collection<?> getValueChoiceTyped(RequiredDelegationConnector object,
					List<AssemblyContext> typedList) {
				//OuterRoles gehen verloren
				ComposedStructure composedStructure = object.getParentStructure__Connector();
				List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
				OperationRequiredRole outerRole = object.getOuterRequiredRole_RequiredDelegationConnector();
				if (outerRole != null) {
					Predicate<OperationRequiredRole> predicate = role -> role
		    				.getRequiredInterface__OperationRequiredRole()
		    				.isAssignableFrom(outerRole.getRequiredInterface__OperationRequiredRole());
					contexts = contexts
            				.stream()
            				.filter(context -> context.getEncapsulatedComponent__AssemblyContext()
            						.getRequiredRoles_InterfaceRequiringEntity().stream().filter(OperationRequiredRole.class::isInstance)
            						.map(OperationRequiredRole.class::cast).anyMatch(predicate))
            				.collect(Collectors.toList());
				}
				OperationRequiredRole innerRole = object.getInnerRequiredRole_RequiredDelegationConnector();
				if (innerRole != null) {
					contexts = contexts.stream().filter(c -> c.getEncapsulatedComponent__AssemblyContext()
							.getRequiredRoles_InterfaceRequiringEntity()
							.stream()
							.filter(OperationRequiredRole.class::isInstance)
							.map(OperationRequiredRole.class::cast)
							.anyMatch(role -> role == innerRole)).collect(Collectors.toList());
				}
				return contexts;
			}
			
		});
	}

		
}
