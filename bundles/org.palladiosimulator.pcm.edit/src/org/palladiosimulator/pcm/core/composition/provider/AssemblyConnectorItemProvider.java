package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Role;

import tools.mdsd.library.emfeditutils.itempropertydescriptor.ItemPropertyDescriptorUtils;
import tools.mdsd.library.emfeditutils.itempropertydescriptor.ValueChoiceCalculatorBase;

public class AssemblyConnectorItemProvider extends AssemblyConnectorItemProviderGen {
    /**
     * Call sources, methods call helper Methods with.
     * 
     * @author Nathan
     *
     */
    private enum RequiringProviding {
        /**
         * addRequiring[...] is calling the helper Method
         */
        REQUIRING,
        /**
         * addProviding[...] is calling the helper Method
         */
        PROVIDING;
    }

    public AssemblyConnectorItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor(Object object) {
        super.addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator
            .setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, AssemblyContext.class) {
                @Override
                protected Collection<?> getValueChoiceTyped(AssemblyConnector object, List<AssemblyContext> typedList) {
                    ComposedStructure composedStructure = object.getParentStructure__Connector();
                    List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
                    AssemblyContext providingContext = object.getProvidingAssemblyContext_AssemblyConnector();
                    List<OperationInterface> providingContextInterfaces;
                    if (providingContext != null) {
                        contexts = getCopyWithoutContext(contexts, object, RequiringProviding.PROVIDING);
                        List<ProvidedRole> providingContextRoles = providingContext
                            .getEncapsulatedComponent__AssemblyContext()
                            .getProvidedRoles_InterfaceProvidingEntity()
                            .stream()
                            .filter(OperationProvidedRole.class::isInstance)
                            .map(OperationProvidedRole.class::cast)
                            .collect(Collectors.toList());
                        providingContextInterfaces = providingContextRoles.stream()
                            .map(OperationProvidedRole.class::cast)
                            .map(OperationProvidedRole::getProvidedInterface__OperationProvidedRole)
                            .collect(Collectors.toList());
                        Set<AssemblyContext> contexts2 = new HashSet<AssemblyContext>();
                        for (OperationInterface oi : providingContextInterfaces) {
                            for (AssemblyContext c : contexts) {
                                if (c.getEncapsulatedComponent__AssemblyContext()
                                    .getRequiredRoles_InterfaceRequiringEntity()
                                    .stream()
                                    .filter(OperationRequiredRole.class::isInstance)
                                    .map(OperationRequiredRole.class::cast)
                                    .anyMatch(opr -> opr.getRequiredInterface__OperationRequiredRole()
                                        .isAssignableFrom(oi))) {
                                    contexts2.add(c);
                                }
                            }
                        }
                        contexts.clear();
                        contexts.addAll(contexts2);
                    }
                    OperationRequiredRole requiredRole = object.getRequiredRole_AssemblyConnector();
                    if (requiredRole != null) {
                        contexts = contexts.stream()
                            .filter(contextRequiringRolesAssignableFromRole(object, RequiringProviding.REQUIRING))
                            .collect(Collectors.toList());
                    }
                    OperationProvidedRole providedRole = object.getProvidedRole_AssemblyConnector();
                    if (providedRole != null) {
                        contexts = contexts.stream()
                            .filter(contextRequiringRolesAssignableFromRole(object, RequiringProviding.PROVIDING))
                            .collect(Collectors.toList());
                    }
                    return contexts;

                }
            });
    }

    @Override
    protected void addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(Object object) {
        super.addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator
            .setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, AssemblyContext.class) {
                @Override
                protected Collection<?> getValueChoiceTyped(AssemblyConnector object, List<AssemblyContext> typedList) {
                    ComposedStructure composedStructure = object.getParentStructure__Connector();
                    Collection<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
                    AssemblyContext requiringContext = object.getRequiringAssemblyContext_AssemblyConnector();
                    List<OperationInterface> requiringContextInterfaces;
                    if (requiringContext != null) {
                        contexts = getCopyWithoutContext(contexts, object, RequiringProviding.REQUIRING);
                        List<RequiredRole> requiringContextRoles = requiringContext
                            .getEncapsulatedComponent__AssemblyContext()
                            .getRequiredRoles_InterfaceRequiringEntity()
                            .stream()
                            .filter(OperationRequiredRole.class::isInstance)
                            .map(OperationRequiredRole.class::cast)
                            .collect(Collectors.toList());
                        requiringContextInterfaces = requiringContextRoles.stream()
                            .map(OperationRequiredRole.class::cast)
                            .map(OperationRequiredRole::getRequiredInterface__OperationRequiredRole)
                            .collect(Collectors.toList());
                        // Removve all contexts without match in the requiringContextInterfaces List
                        Set<AssemblyContext> contexts2 = new HashSet<AssemblyContext>();
                        for (OperationInterface oi : requiringContextInterfaces) {
                            for (AssemblyContext c : contexts) {
                                if (c.getEncapsulatedComponent__AssemblyContext()
                                    .getProvidedRoles_InterfaceProvidingEntity()
                                    .stream()
                                    .filter(OperationProvidedRole.class::isInstance)
                                    .map(OperationProvidedRole.class::cast)
                                    .anyMatch(opr -> opr.getProvidedInterface__OperationProvidedRole()
                                        .isAssignableFrom(oi))) {
                                    contexts2.add(c);
                                }
                            }
                        }
                        contexts.clear();
                        contexts.addAll(contexts2);
                    }
                    OperationProvidedRole providedRole = object.getProvidedRole_AssemblyConnector();
                    if (providedRole != null) {
                        contexts = contexts.stream()
                            .filter(contextProvidingRolesAssignableFromRole(object, RequiringProviding.PROVIDING))
                            .collect(Collectors.toList());
                    }
                    OperationRequiredRole requiredRole = object.getRequiredRole_AssemblyConnector();
                    if (requiredRole != null) {
                        contexts = contexts.stream()
                            .filter(contextProvidingRolesAssignableFromRole(object, RequiringProviding.REQUIRING))
                            .collect(Collectors.toList());
                    }
                    return contexts;
                }
            });
    }

    @Override
    protected void addProvidedRole_AssemblyConnectorPropertyDescriptor(Object object) {
        super.addProvidedRole_AssemblyConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(AssemblyConnector.class, OperationProvidedRole.class) {
                    @Override
                    protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                            List<OperationProvidedRole> typedList) {
                        ComposedStructure composedStructure = object.getParentStructure__Connector();
                        AssemblyContext requiringContext = object.getRequiringAssemblyContext_AssemblyConnector();
                        Collection<AssemblyContext> contexts = composedStructure
                            .getAssemblyContexts__ComposedStructure();
                        if (requiringContext != null) {
                            getCopyWithoutContext(contexts, object, RequiringProviding.PROVIDING);
                        }
                        Collection<OperationProvidedRole> operationProvidedRoles = contexts.stream()
                            .map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
                            .map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity)
                            .flatMap(List::stream)
                            .filter(OperationProvidedRole.class::isInstance)
                            .map(OperationProvidedRole.class::cast)
                            .collect(Collectors.toSet());
                        Collection<RepositoryComponent> components = contexts.stream()
                            .map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
                            .collect(Collectors.toList());
                        AssemblyContext providingContext = object.getProvidingAssemblyContext_AssemblyConnector();

                        if (providingContext != null) {
                            operationProvidedRoles = providingContext.getEncapsulatedComponent__AssemblyContext()
                                .getProvidedRoles_InterfaceProvidingEntity()
                                .stream()
                                .filter(OperationProvidedRole.class::isInstance)
                                .map(OperationProvidedRole.class::cast)
                                .collect(Collectors.toList());
                        }
                        if (requiringContext != null) {
                            List<RequiredRole> requiringContextsRoles = requiringContext
                                .getEncapsulatedComponent__AssemblyContext()
                                .getRequiredRoles_InterfaceRequiringEntity();
                            Collection<OperationInterface> requiringContextsInterfaces = requiringContextsRoles.stream()
                                .filter(OperationRequiredRole.class::isInstance)
                                .map(OperationRequiredRole.class::cast)
                                .map(orr -> orr.getRequiredInterface__OperationRequiredRole())
                                .collect(Collectors.toList());
                            Set<OperationProvidedRole> filteredRoles = new HashSet<OperationProvidedRole>();
                            for (OperationInterface oi : requiringContextsInterfaces) {
                                for (OperationProvidedRole role : operationProvidedRoles) {
                                    if (role.getProvidedInterface__OperationProvidedRole()
                                        .isAssignableFrom(oi)) {
                                        filteredRoles.add(role);
                                    }
                                }
                            }
                            operationProvidedRoles = filteredRoles;
                        }
                        Role requiringRole = object.getRequiredRole_AssemblyConnector();
                        if (requiringRole != null) {
                            OperationInterface requiringInterface = object.getRequiredRole_AssemblyConnector()
                                .getRequiredInterface__OperationRequiredRole();
                            operationProvidedRoles = operationProvidedRoles.stream()
                                .filter(opr -> opr.getProvidedInterface__OperationProvidedRole()
                                    .isAssignableFrom(requiringInterface))
                                .collect(Collectors.toList());
                        }
                        return operationProvidedRoles;
                    }
                });
    }

    @Override
    protected void addRequiredRole_AssemblyConnectorPropertyDescriptor(Object object) {
        super.addRequiredRole_AssemblyConnectorPropertyDescriptor(object);
        var decorator = ItemPropertyDescriptorUtils.decorateLastDescriptor(this.itemPropertyDescriptors);
        decorator.setValueChoiceCalculator(
                new ValueChoiceCalculatorBase<>(AssemblyConnector.class, OperationRequiredRole.class) {
                    @Override
                    protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                            List<OperationRequiredRole> typedList) {
                        ComposedStructure composedStructure = object.getParentStructure__Connector();
                        AssemblyContext providingContext = object.getProvidingAssemblyContext_AssemblyConnector();
                        Collection<AssemblyContext> contexts = composedStructure
                            .getAssemblyContexts__ComposedStructure();
                        if (providingContext != null) {
                            getCopyWithoutContext(contexts, object, RequiringProviding.REQUIRING);
                        }
                        Collection<OperationRequiredRole> operationRequiredRoles = contexts.stream()
                            .map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
                            .map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity)
                            .flatMap(List::stream)
                            .map(OperationRequiredRole.class::cast)
                            .collect(Collectors.toSet());
                        Collection<RepositoryComponent> components = contexts.stream()
                            .map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
                            .collect(Collectors.toList());
                        AssemblyContext requiringContext = object.getRequiringAssemblyContext_AssemblyConnector();
                        if (requiringContext != null) {
                            operationRequiredRoles = requiringContext.getEncapsulatedComponent__AssemblyContext()
                                .getRequiredRoles_InterfaceRequiringEntity()
                                .stream()
                                .filter(OperationRequiredRole.class::isInstance)
                                .map(OperationRequiredRole.class::cast)
                                .collect(Collectors.toList());
                        }
                        if (providingContext != null) {
                            List<ProvidedRole> providingContextsRoles = providingContext
                                .getEncapsulatedComponent__AssemblyContext()
                                .getProvidedRoles_InterfaceProvidingEntity();
                            Collection<OperationInterface> providingContextsInterfaces = providingContextsRoles.stream()
                                .filter(OperationProvidedRole.class::isInstance)
                                .map(OperationProvidedRole.class::cast)
                                .map(opr -> opr.getProvidedInterface__OperationProvidedRole())
                                .collect(Collectors.toList());
                            Set<OperationRequiredRole> filteredRoles = new HashSet<OperationRequiredRole>();
                            for (OperationInterface oi : providingContextsInterfaces) {
                                for (OperationRequiredRole role : operationRequiredRoles) {
                                    if (role.getRequiredInterface__OperationRequiredRole()
                                        .isAssignableFrom(oi)) {
                                        filteredRoles.add(role);
                                    }
                                }
                            }
                            operationRequiredRoles = filteredRoles;
                        }
                        Role providingRole = object.getProvidedRole_AssemblyConnector();
                        if (providingRole != null) {
                            OperationInterface providingInterface = object.getProvidedRole_AssemblyConnector()
                                .getProvidedInterface__OperationProvidedRole();
                            operationRequiredRoles = operationRequiredRoles.stream()
                                .filter(orr -> orr.getRequiredInterface__OperationRequiredRole()
                                    .isAssignableFrom(providingInterface))
                                .collect(Collectors.toList());
                        }
                        return operationRequiredRoles;
                    }
                });
    }

    /**
     * This method returns a copy of the list without the AssemblyConnector's context.
     * @param contexts list of contexts
     * @param connector
     * @param flag
     * @return 
     */
    private List<AssemblyContext> getCopyWithoutContext(Collection<AssemblyContext> contexts, AssemblyConnector connector,
            RequiringProviding flag) {
        AssemblyContext myContext;
        if (flag == RequiringProviding.REQUIRING) {
            myContext = connector.getRequiringAssemblyContext_AssemblyConnector();
        } else {
            myContext = connector.getProvidingAssemblyContext_AssemblyConnector();
        }
        return contexts.stream()
            .filter(c -> c != myContext)
            .collect(Collectors.toList());
    }

    /**
     * This method returns a predicate, that allows all contexts which have a providing role,
     * that matches the assemblyConnectors requiring or providing role, depending on the flag.
     * @param connector
     * @param flag
     * @return
     */
    private Predicate<AssemblyContext> contextProvidingRolesAssignableFromRole(AssemblyConnector connector,
            RequiringProviding flag) {
        if (flag == RequiringProviding.PROVIDING) {
            OperationProvidedRole providedRole = connector.getProvidedRole_AssemblyConnector();
            Predicate<OperationProvidedRole> predicate = role -> role.getProvidedInterface__OperationProvidedRole()
                .isAssignableFrom(providedRole.getProvidedInterface__OperationProvidedRole());
            return context -> context.getEncapsulatedComponent__AssemblyContext()
                .getProvidedRoles_InterfaceProvidingEntity()
                .stream()
                .filter(OperationProvidedRole.class::isInstance)
                .map(OperationProvidedRole.class::cast)
                .anyMatch(predicate);
        } else {
            OperationRequiredRole requiredRole = connector.getRequiredRole_AssemblyConnector();
            Predicate<OperationProvidedRole> predicate = role -> role.getProvidedInterface__OperationProvidedRole()
                .isAssignableFrom(requiredRole.getRequiredInterface__OperationRequiredRole());
            return context -> context.getEncapsulatedComponent__AssemblyContext()
                .getProvidedRoles_InterfaceProvidingEntity()
                .stream()
                .filter(OperationProvidedRole.class::isInstance)
                .map(OperationProvidedRole.class::cast)
                .anyMatch(predicate);
        }
    }

    /**
     * This method returns a predicate, that allows all contexts which have a requiring role,
     * that matches the assemblyConnectors requiring or providing role, depending on the flag.
     * @param connector
     * @param flag
     * @return 
     */
    private Predicate<AssemblyContext> contextRequiringRolesAssignableFromRole(AssemblyConnector connector,
            RequiringProviding flag) {
        if (flag == RequiringProviding.PROVIDING) {
            OperationProvidedRole providedRole = connector.getProvidedRole_AssemblyConnector();
            Predicate<OperationRequiredRole> predicate = role -> role.getRequiredInterface__OperationRequiredRole()
                .isAssignableFrom(providedRole.getProvidedInterface__OperationProvidedRole());
            return context -> context.getEncapsulatedComponent__AssemblyContext()
                .getRequiredRoles_InterfaceRequiringEntity()
                .stream()
                .filter(OperationRequiredRole.class::isInstance)
                .map(OperationRequiredRole.class::cast)
                .anyMatch(predicate);
        } else {
            OperationRequiredRole requiredRole = connector.getRequiredRole_AssemblyConnector();
            Predicate<OperationRequiredRole> predicate = role -> role.getRequiredInterface__OperationRequiredRole()
                .isAssignableFrom(requiredRole.getRequiredInterface__OperationRequiredRole());
            return context -> context.getEncapsulatedComponent__AssemblyContext()
                .getRequiredRoles_InterfaceRequiringEntity()
                .stream()
                .filter(OperationRequiredRole.class::isInstance)
                .map(OperationRequiredRole.class::cast)
                .anyMatch(predicate);
        }
    }
}
