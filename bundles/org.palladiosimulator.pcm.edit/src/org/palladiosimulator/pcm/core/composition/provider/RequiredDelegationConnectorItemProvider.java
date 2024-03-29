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
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class RequiredDelegationConnectorItemProvider extends RequiredDelegationConnectorItemProviderGen {

    public RequiredDelegationConnectorItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addInnerRequiredRole_RequiredDelegationConnectorPropertyDescriptor(Object object) {
        super.addInnerRequiredRole_RequiredDelegationConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(RequiredDelegationConnector.class, OperationRequiredRole.class) {

                    @Override
                    protected Collection<?> getValueChoiceTyped(RequiredDelegationConnector object,
                            List<OperationRequiredRole> typedList) {
                        ComposedStructure composedStructure = object.getParentStructure__Connector();
                        Collection<AssemblyContext> contexts = composedStructure
                            .getAssemblyContexts__ComposedStructure();
                        Collection<OperationRequiredRole> operationRequiredRoles = contexts.stream()
                            .map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
                            .map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity)
                            .flatMap(List::stream)
                            .map(OperationRequiredRole.class::cast)
                            .collect(Collectors.toSet());

                        AssemblyContext context = object.getAssemblyContext_RequiredDelegationConnector();
                        if (context != null) {
                            Collection<OperationRequiredRole> contextsRoles = context
                                .getEncapsulatedComponent__AssemblyContext()
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
                                .filter(opr -> opr.getRequiredInterface__OperationRequiredRole()
                                    .isAssignableFrom(outerRole.getRequiredInterface__OperationRequiredRole()))
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
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(RequiredDelegationConnector.class, OperationRequiredRole.class) {

                    @Override
                    protected Collection<?> getValueChoiceTyped(RequiredDelegationConnector object,
                            List<OperationRequiredRole> typedList) {
                        AssemblyContext context = object.getAssemblyContext_RequiredDelegationConnector();
                        ComposedStructure parentStructure = object.getParentStructure__Connector();
                        ComposedProvidingRequiringEntity requiringParentStructure;
                        if (parentStructure instanceof ComposedProvidingRequiringEntity) {
                            requiringParentStructure = (ComposedProvidingRequiringEntity) parentStructure;
                        } else {
                            return new ArrayList<OperationRequiredRole>();
                        }
                        Collection<OperationRequiredRole> outerRequiredRoles = requiringParentStructure
                            .getRequiredRoles_InterfaceRequiringEntity()
                            .stream()
                            .filter(OperationRequiredRole.class::isInstance)
                            .map(OperationRequiredRole.class::cast)
                            .collect(Collectors.toList());

                        if (context != null) {
                            Collection<OperationRequiredRole> contextsRoles = context
                                .getEncapsulatedComponent__AssemblyContext()
                                .getRequiredRoles_InterfaceRequiringEntity()
                                .stream()
                                .filter(OperationRequiredRole.class::isInstance)
                                .map(OperationRequiredRole.class::cast)
                                .collect(Collectors.toList());
                            Collection<OperationInterface> contextsInterfaces = contextsRoles.stream()
                                .map(orr -> orr.getRequiredInterface__OperationRequiredRole())
                                .collect(Collectors.toList());
                            Set<OperationRequiredRole> filteredRoles = new HashSet<OperationRequiredRole>();
                            for (OperationInterface oi : contextsInterfaces) {
                                for (OperationRequiredRole role : outerRequiredRoles) {
                                    if (role.getRequiredInterface__OperationRequiredRole()
                                        .isAssignableFrom(oi)) {
                                        filteredRoles.add(role);
                                    }
                                }
                            }
                            outerRequiredRoles = filteredRoles;
                        }

                        OperationRequiredRole innerRole = object.getInnerRequiredRole_RequiredDelegationConnector();
                        if (innerRole != null) {
                        	outerRequiredRoles = outerRequiredRoles.stream()
                                .filter(opr -> opr.getRequiredInterface__OperationRequiredRole()
                                    .isAssignableFrom(innerRole.getRequiredInterface__OperationRequiredRole()))
                                .collect(Collectors.toList());
                        }
                        return outerRequiredRoles;
                    }
                });
    }

    @Override
    protected void addAssemblyContext_RequiredDelegationConnectorPropertyDescriptor(Object object) {
        super.addAssemblyContext_RequiredDelegationConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(RequiredDelegationConnector.class, AssemblyContext.class) {

                    @Override
                    protected Collection<?> getValueChoiceTyped(RequiredDelegationConnector object,
                            List<AssemblyContext> typedList) {
                        ComposedStructure composedStructure = object.getParentStructure__Connector();
                        List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
                        OperationRequiredRole outerRole = object.getOuterRequiredRole_RequiredDelegationConnector();
                        if (outerRole != null) {
                            Predicate<OperationRequiredRole> predicate = role -> role
                                .getRequiredInterface__OperationRequiredRole()
                                .isAssignableFrom(outerRole.getRequiredInterface__OperationRequiredRole());
                            contexts = contexts.stream()
                                .filter(context -> context.getEncapsulatedComponent__AssemblyContext()
                                    .getRequiredRoles_InterfaceRequiringEntity()
                                    .stream()
                                    .filter(OperationRequiredRole.class::isInstance)
                                    .map(OperationRequiredRole.class::cast)
                                    .anyMatch(predicate))
                                .collect(Collectors.toList());
                        }
                        OperationRequiredRole innerRole = object.getInnerRequiredRole_RequiredDelegationConnector();
                        if (innerRole != null) {
                            contexts = contexts.stream()
                                .filter(c -> c.getEncapsulatedComponent__AssemblyContext()
                                    .getRequiredRoles_InterfaceRequiringEntity()
                                    .stream()
                                    .filter(OperationRequiredRole.class::isInstance)
                                    .map(OperationRequiredRole.class::cast)
                                    .anyMatch(role -> role == innerRole))
                                .collect(Collectors.toList());
                        }
                        return contexts;
                    }

                });
    }

}
