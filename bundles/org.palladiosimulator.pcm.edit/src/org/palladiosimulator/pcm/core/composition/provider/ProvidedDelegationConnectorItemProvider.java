package org.palladiosimulator.pcm.core.composition.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class ProvidedDelegationConnectorItemProvider extends ProvidedDelegationConnectorItemProviderGen {

    public ProvidedDelegationConnectorItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addInnerProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(Object object) {
        super.addInnerProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(ProvidedDelegationConnector.class, OperationProvidedRole.class) {

                    @Override
                    protected Collection<?> getValueChoiceTyped(ProvidedDelegationConnector object,
                            List<OperationProvidedRole> typedList) {
                        ComposedStructure composedStructure = object.getParentStructure__Connector();
                        Collection<AssemblyContext> contexts = composedStructure
                            .getAssemblyContexts__ComposedStructure();
                        Collection<OperationProvidedRole> operationProvidedRoles = contexts.stream()
                            .map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
                            .map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity)
                            .flatMap(List::stream)
                            .map(OperationProvidedRole.class::cast)
                            .collect(Collectors.toSet());

                        AssemblyContext context = object.getAssemblyContext_ProvidedDelegationConnector();
                        if (context != null) {
                            Collection<OperationProvidedRole> contextsRoles = context
                                .getEncapsulatedComponent__AssemblyContext()
                                .getProvidedRoles_InterfaceProvidingEntity()
                                .stream()
                                .filter(OperationProvidedRole.class::isInstance)
                                .map(OperationProvidedRole.class::cast)
                                .collect(Collectors.toList());
                            operationProvidedRoles = contextsRoles;
                        }
                        OperationProvidedRole outerRole = object.getOuterProvidedRole_ProvidedDelegationConnector();
                        if (outerRole != null) {
                            operationProvidedRoles = operationProvidedRoles.stream()
                                .filter(opr -> opr.getProvidedInterface__OperationProvidedRole()
                                    .isAssignableFrom(outerRole.getProvidedInterface__OperationProvidedRole()))
                                .collect(Collectors.toList());
                        }
                        return operationProvidedRoles;
                    }
                });
    }

    @Override
    protected void addOuterProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(Object object) {
        super.addOuterProvidedRole_ProvidedDelegationConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(ProvidedDelegationConnector.class, OperationProvidedRole.class) {

                    @Override
                    protected Collection<?> getValueChoiceTyped(ProvidedDelegationConnector object,
                            List<OperationProvidedRole> typedList) {
                        AssemblyContext context = object.getAssemblyContext_ProvidedDelegationConnector();
                        ComposedStructure parentStructure = object.getParentStructure__Connector();
                        ComposedProvidingRequiringEntity providingParentStructure;
                        if (parentStructure instanceof ComposedProvidingRequiringEntity) {
                            providingParentStructure = (ComposedProvidingRequiringEntity) parentStructure;
                        } else {
                            return new ArrayList<OperationProvidedRole>();
                        }
                        Collection<OperationProvidedRole> outerProvidedRoles = providingParentStructure
                            .getProvidedRoles_InterfaceProvidingEntity()
                            .stream()
                            .filter(OperationProvidedRole.class::isInstance)
                            .map(OperationProvidedRole.class::cast)
                            .collect(Collectors.toList());
                        if (context != null) {
                            Collection<OperationProvidedRole> contextsRoles = context
                                .getEncapsulatedComponent__AssemblyContext()
                                .getProvidedRoles_InterfaceProvidingEntity()
                                .stream()
                                .filter(OperationProvidedRole.class::isInstance)
                                .map(OperationProvidedRole.class::cast)
                                .collect(Collectors.toList());
                            Collection<OperationInterface> contextsInterfaces = contextsRoles.stream()
                                .map(opr -> opr.getProvidedInterface__OperationProvidedRole())
                                .collect(Collectors.toList());
                            Set<OperationProvidedRole> filteredRoles = new HashSet<OperationProvidedRole>();
                            for (OperationInterface oi : contextsInterfaces) {
                                for (OperationProvidedRole role : outerProvidedRoles) {
                                    if (role.getProvidedInterface__OperationProvidedRole()
                                        .isAssignableFrom(oi)) {
                                        filteredRoles.add(role);
                                    }
                                }
                            }
                            outerProvidedRoles = filteredRoles;
                        }
                        OperationProvidedRole innerRole = object.getInnerProvidedRole_ProvidedDelegationConnector();
                        if (innerRole != null) {
                        	outerProvidedRoles = outerProvidedRoles.stream()
                                .filter(opr -> opr.getProvidedInterface__OperationProvidedRole()
                                    .isAssignableFrom(innerRole.getProvidedInterface__OperationProvidedRole()))
                                .collect(Collectors.toList());
                        }
                        return outerProvidedRoles;
                    }
                });
    }

    @Override
    protected void addAssemblyContext_ProvidedDelegationConnectorPropertyDescriptor(Object object) {
        super.addAssemblyContext_ProvidedDelegationConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(ProvidedDelegationConnector.class, AssemblyContext.class) {

                    @Override
                    protected Collection<?> getValueChoiceTyped(ProvidedDelegationConnector object,
                            List<AssemblyContext> typedList) {
                        ComposedStructure composedStructure = object.getParentStructure__Connector();
                        List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
                        OperationProvidedRole outerRole = object.getOuterProvidedRole_ProvidedDelegationConnector();
                        if (outerRole != null) {
                            Predicate<OperationProvidedRole> predicate = role -> role
                                .getProvidedInterface__OperationProvidedRole()
                                .isAssignableFrom(outerRole.getProvidedInterface__OperationProvidedRole());
                            contexts = contexts.stream()
                                .filter(context -> context.getEncapsulatedComponent__AssemblyContext()
                                    .getProvidedRoles_InterfaceProvidingEntity()
                                    .stream()
                                    .filter(OperationProvidedRole.class::isInstance)
                                    .map(OperationProvidedRole.class::cast)
                                    .anyMatch(predicate))
                                .collect(Collectors.toList());
                        }
                        OperationProvidedRole innerRole = object.getInnerProvidedRole_ProvidedDelegationConnector();
                        if (innerRole != null) {
                            contexts = contexts.stream()
                                .filter(c -> c.getEncapsulatedComponent__AssemblyContext()
                                    .getProvidedRoles_InterfaceProvidingEntity()
                                    .stream()
                                    .filter(OperationProvidedRole.class::isInstance)
                                    .map(OperationProvidedRole.class::cast)
                                    .anyMatch(role -> role == innerRole))
                                .collect(Collectors.toList());
                        }
                        return contexts;
                    }

                });
    }

}
