package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
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
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, AssemblyContext.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<AssemblyContext> typedList) {
				ComposedStructure composedStructure = object.getParentStructure__Connector();		
				List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
				AssemblyContext providingContext = object.getProvidingAssemblyContext_AssemblyConnector();
				List<OperationInterface> providingContextInterfaces;
				if (providingContext != null) {
					contexts = removeContext(contexts, object, RequiringProviding.PROVIDING);
					List<ProvidedRole> providingContextRoles = providingContext
							.getEncapsulatedComponent__AssemblyContext()
							.getProvidedRoles_InterfaceProvidingEntity()
							.stream()
							.filter(OperationProvidedRole.class::isInstance)
							.map(OperationProvidedRole.class::cast).collect(Collectors.toList());
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
            						.anyMatch(opr -> opr.getRequiredInterface__OperationRequiredRole() == oi)) {
            					contexts2.add(c);
            				}
            			}
            		}
            		contexts.clear();
            		contexts.addAll(contexts2);
				}
				OperationRequiredRole requiredRole = object.getRequiredRole_AssemblyConnector();
				if (requiredRole != null) {
					contexts = contexts
							.stream()
							.filter(c -> c.getEncapsulatedComponent__AssemblyContext()
									.getRequiredRoles_InterfaceRequiringEntity()
									.stream()
									.filter(OperationRequiredRole.class::isInstance)
									.map(OperationRequiredRole.class::cast)
									.anyMatch(orr -> orr.getRequiredInterface__OperationRequiredRole() == requiredRole.getRequiredInterface__OperationRequiredRole()))
							.collect(Collectors.toList());
				}
				OperationProvidedRole providedRole = object.getProvidedRole_AssemblyConnector();
				if (providedRole != null) {
					contexts = contexts.stream()
							.filter(c -> c.getEncapsulatedComponent__AssemblyContext()
									.getRequiredRoles_InterfaceRequiringEntity()
									.stream()
									.filter(OperationRequiredRole.class::isInstance)
									.map(OperationRequiredRole.class::cast)
									.anyMatch(orr -> orr.getRequiredInterface__OperationRequiredRole() == providedRole.getProvidedInterface__OperationProvidedRole()))
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
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, AssemblyContext.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<AssemblyContext> typedList) {
				// Für Filterschritte private Methoden bauen die predicates zurückggeben, jenachdem welcher Filter bruacht.
				// Stream in Variable und pro gesetztem Wert die Filter hinzufügen.
            	ComposedStructure composedStructure = object.getParentStructure__Connector();
            	List<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	AssemblyContext requiringContext = object.getRequiringAssemblyContext_AssemblyConnector();
            	List<OperationInterface> requiringContextInterfaces;
            	if (requiringContext != null) {
            		contexts = removeContext(contexts, object, RequiringProviding.REQUIRING);
            		List<RequiredRole> requiringContextRoles = requiringContext
            				.getEncapsulatedComponent__AssemblyContext()
            				.getRequiredRoles_InterfaceRequiringEntity()
            				.stream()
            				.filter(OperationRequiredRole.class::isInstance)
                			.map(OperationRequiredRole.class::cast).collect(Collectors.toList());
            		requiringContextInterfaces = requiringContextRoles.stream()
            				.map(OperationRequiredRole.class::cast)
            				.map(OperationRequiredRole::getRequiredInterface__OperationRequiredRole)
            				.collect(Collectors.toList());
            		//Schmeiße alle Contexte raus, die keinen Match mit der requiringContextInterfaces Liste haben:
            		Set<AssemblyContext> contexts2 = new HashSet<AssemblyContext>();
            		for (OperationInterface oi : requiringContextInterfaces) {
            			for (AssemblyContext c : contexts) {
            				if (c.getEncapsulatedComponent__AssemblyContext()
            						.getProvidedRoles_InterfaceProvidingEntity()
            						.stream()
            						.filter(OperationProvidedRole.class::isInstance)
            						.map(OperationProvidedRole.class::cast)
            						.anyMatch(opr -> opr.getProvidedInterface__OperationProvidedRole() == oi)) {
            					contexts2.add(c);
            				}
            			}
            		}
            		contexts.clear();
            		contexts.addAll(contexts2);
            	}
            	OperationProvidedRole providedRole = object.getProvidedRole_AssemblyConnector();
            	if (providedRole != null) {
            		contexts = contexts
            				.stream()
            				.filter(c -> c.getEncapsulatedComponent__AssemblyContext()
            						.getProvidedRoles_InterfaceProvidingEntity()
            						.stream()
            						.filter(OperationProvidedRole.class::isInstance)
            						.map(OperationProvidedRole.class::cast)
            						.anyMatch(opr -> opr.getProvidedInterface__OperationProvidedRole() == providedRole.getProvidedInterface__OperationProvidedRole()))
            				.collect(Collectors.toList());
            	}
            	OperationRequiredRole requiredRole = object.getRequiredRole_AssemblyConnector();
            	if (requiredRole != null) {
            		contexts = contexts.stream()
            				.filter(c -> c.getEncapsulatedComponent__AssemblyContext()
            						.getProvidedRoles_InterfaceProvidingEntity()
            						.stream()
            						.filter(OperationProvidedRole.class::isInstance)
            						.map(OperationProvidedRole.class::cast)
            						.anyMatch(opr -> opr.getProvidedInterface__OperationProvidedRole() == requiredRole.getRequiredInterface__OperationRequiredRole()))
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
		decorator.setValueChoiceCalculator(new ValueChoiceCalculatorBase<>(AssemblyConnector.class, OperationProvidedRole.class) {
			@Override
            protected Collection<?> getValueChoiceTyped(AssemblyConnector object,
                    List<OperationProvidedRole> typedList) {
				
				ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	AssemblyContext myContext = object.getRequiringAssemblyContext_AssemblyConnector();
            	if (myContext != null) {
            		removeContext(contexts, object, RequiringProviding.PROVIDING);
            	}
            	List<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toList());
            	// Set<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toSet());
            	// Hier gab es den Vorschlag ein Set zu nehmen. Ich habe mich dagegen entschieden, da bspw. eine durch einen Context gefangene Komponente mehrmals vorkommen kann im selben Diagramm.
            	// Siehe MediaManagement und AssemblyMediaManagement.
            	
				Role myRole = object.getRequiredRole_AssemblyConnector();
				if(myRole == null) {
					return components.stream()
	            			.map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity).flatMap(List::stream).collect(Collectors.toList());
				}
				OperationInterface myInterface = object.getRequiredRole_AssemblyConnector().getRequiredInterface__OperationRequiredRole();
				if (myInterface == null) {
					return components.stream()
	            			.map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity).collect(Collectors.toList());
				}
				// Umschreiben auf Optional.ofNullable alles schön in eine Zeile.
            	
            	//Wie bekomme ich hier jetzt die OperationInterfaces der basicComponents? RepositoryPackage.Literals.OPERATION_PROVIDED_ROLE.isInstance(object)
            	List<ProvidedRole> operationProvidedRoles = components.stream()
            			.map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity)
            			.flatMap(List::stream)
            			.filter(OperationProvidedRole.class::isInstance)
            			.map(OperationProvidedRole.class::cast)
            			//.filter(opr -> opr.getProvidedInterface__OperationProvidedRole() == myInterface) //tatsächlicher Vergleich, sodass auch Kind Schnittstellen gültig sind?
            			.filter(opr -> opr.getProvidedInterface__OperationProvidedRole().isAssignableFrom(myInterface))
            			// führt zu org.eclipse.ocl.pivot.internal.delegate.OCLDelegateException: Failed to create Xtext resource for '_jdvmwCgBEeyGWpe2k9LoxQ.essentialocl'
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
				
				ComposedStructure composedStructure = object.getParentStructure__Connector();
            	EList<AssemblyContext> contexts = composedStructure.getAssemblyContexts__ComposedStructure();
            	AssemblyContext myContext = object.getProvidingAssemblyContext_AssemblyConnector();
            	if (myContext != null) {
            		removeContext(contexts, object, RequiringProviding.REQUIRING);
            	}
            	List<RepositoryComponent> components = contexts.stream().map(AssemblyContext::getEncapsulatedComponent__AssemblyContext).collect(Collectors.toList());
				
            	Role myRole = object.getProvidedRole_AssemblyConnector();
				if(myRole == null) {
					return components.stream()
	            			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity).flatMap(List::stream).collect(Collectors.toList());
				}
				OperationInterface myInterface = object.getProvidedRole_AssemblyConnector().getProvidedInterface__OperationProvidedRole();
				if (myInterface == null) {
					return components.stream()
	            			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity).collect(Collectors.toList());
				}
				
				List<RequiredRole> operationRequiredRoles = components.stream()
            			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity)
            			.flatMap(List::stream)
            			.filter(OperationRequiredRole.class::isInstance)
            			.map(OperationRequiredRole.class::cast)
            			.filter(opr -> opr.getRequiredInterface__OperationRequiredRole() == myInterface)
            			.collect(Collectors.toList());
            	return operationRequiredRoles;
            }
		});
	}

	private List<AssemblyContext> removeContext(List<AssemblyContext> contexts, AssemblyConnector connector, RequiringProviding flag) {
		AssemblyContext myContext;
		if (flag == RequiringProviding.REQUIRING) {
			myContext = connector.getRequiringAssemblyContext_AssemblyConnector();
		}	else {
			myContext = connector.getProvidingAssemblyContext_AssemblyConnector();
		}
		return contexts.stream().filter(c -> c != myContext).collect(Collectors.toList());
	}
	
	
}
