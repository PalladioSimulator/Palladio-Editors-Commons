package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
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
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
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
            	return contexts.stream()
            			.filter(Predicate.not(object.getProvidingAssemblyContext_AssemblyConnector()::equals))
            			.collect(Collectors.toList());
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
            	return contexts.stream()
            			.filter(Predicate.not(object.getRequiringAssemblyContext_AssemblyConnector()::equals))
            			.collect(Collectors.toList());
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
				OperationInterface myInterface = object.getRequiredRole_AssemblyConnector().getRequiredInterface__OperationRequiredRole();
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	Set<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toSet());
            	//Wie bekomme ich hier jetzt die OperationInterfaces der basicComponents? RepositoryPackage.Literals.OPERATION_PROVIDED_ROLE.isInstance(object)
            	List<ProvidedRole> operationProvidedRoles = components.stream()
            			.map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity)
            			.flatMap(List::stream).filter(OperationProvidedRole.class::isInstance)
            			.map(OperationProvidedRole.class::cast)
            			.filter(opr -> opr.getProvidedInterface__OperationProvidedRole().getId() == myInterface.getId())
            			.collect(Collectors.toList());
            	return operationProvidedRoles;
            	//return operationProvidedRoles.stream().filter(opr -> RepositoryPackage.Literals.OPERATION_PROVIDED_ROLE.isInstance(object)).collect(Collectors.toList());
            }
		});
	}

	@Override
	protected void addRequiredRole_AssemblyConnectorPropertyDescriptor(Object object) {
		super.addRequiredRole_AssemblyConnectorPropertyDescriptor(object);
		var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, OperationRequiredRole.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<OperationRequiredRole> typedList) {
				OperationInterface myInterface = object.getProvidedRole_AssemblyConnector().getProvidedInterface__OperationProvidedRole();
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	Set<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toSet());
            	List<RequiredRole> operationRequiredRoles = components.stream()
            			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity)
            			.flatMap(List::stream).filter(OperationRequiredRole.class::isInstance)
            			.map(OperationRequiredRole.class::cast)
            			.filter(opr -> opr.getRequiredInterface__OperationRequiredRole().getId() == myInterface.getId())
            			.collect(Collectors.toList());
            	return operationRequiredRoles;
            }
		});
	}

}
