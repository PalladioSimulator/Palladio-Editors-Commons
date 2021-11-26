package org.palladiosimulator.pcm.edit.provider;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.DelegationConnector;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;


public class TestItemProviderUtilities {

	protected static System loadSystem() {
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("system", new XMIResourceFactoryImpl());
        
        ResourceSet resSet = new ResourceSetImpl();
        Resource resource = resSet.getResource(URI
                .createURI("testmodels/MediaStore-InstantDownloadCache.system"), true);
        
        System testSystem = (System) resource.getContents().get(0);
        return testSystem;
	}
	
	protected static Repository loadRepository() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("repository", new XMIResourceFactoryImpl());
        
        ResourceSet resSet = new ResourceSetImpl();
        Resource resource = resSet.getResource(URI
                .createURI("testmodels/MediaStore.repository"), true);
        
        Repository testRepository = (Repository) resource.getContents().get(0);
        return testRepository;
	}
	
	protected static UsageModel loadUsageModel() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("usagemodel", new XMIResourceFactoryImpl());
        
        ResourceSet resSet = new ResourceSetImpl();
        Resource resource = resSet.getResource(URI
                .createURI("testmodels/MediaStoreInstant-DownloadCache.usagemodel"), true);
        
        UsageModel testUsageModel = (UsageModel) resource.getContents().get(0);
        return testUsageModel;
	}
	
	
	
	protected static AssemblyConnector getAssemblyConnector(String id, System system) {
		List<Connector> connectors = system.getConnectors__ComposedStructure();
		for (Connector c : connectors) {
			if (c instanceof AssemblyConnector && c.getId().equals(id)) {
				return (AssemblyConnector) c;
			}
		}
		return null;
	}
	
	protected static DelegationConnector getDelegationConnector(String id, System system) {
		List<Connector> connectors = system.getConnectors__ComposedStructure();
		for (Connector c : connectors) {
			if (c instanceof DelegationConnector && c.getId().equals(id)) {
				return (DelegationConnector) c;
			}
		}
		return null;
	}

	protected static AssemblyContext getAssemblyContext(String id, System system) {
		List<AssemblyContext> contexts = system.getAssemblyContexts__ComposedStructure();
		for (AssemblyContext ac : contexts) {
			if (ac.getId().equals(id)) {
				return ac;
			}
		}
		return null;
	}
	
	protected static ProvidedRole getProvidedRole(String id, Repository repo) {
		List<RepositoryComponent> components = repo.getComponents__Repository();
		Set<ProvidedRole> roles = components.stream().map(RepositoryComponent::getProvidedRoles_InterfaceProvidingEntity).flatMap(List::stream).collect(Collectors.toSet());
		for (ProvidedRole r : roles) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}
	
	protected static OperationRequiredRole getOperationRequiredRole (String id, System system) {
		List<AssemblyContext> contexts = system.getAssemblyContexts__ComposedStructure();
		Collection<OperationRequiredRole> roles = contexts.stream()
    			.map(AssemblyContext::getEncapsulatedComponent__AssemblyContext)
    			.map(RepositoryComponent::getRequiredRoles_InterfaceRequiringEntity)
    			.flatMap(List::stream).map(OperationRequiredRole.class::cast)
    			.collect(Collectors.toSet());
		for (OperationRequiredRole r : roles) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}
	
	protected static ResourceDemandingBehaviour getResourceDemandingBehaviour(String id, Repository repository) {
		List<RepositoryComponent> components = repository.getComponents__Repository();
		List<BasicComponent> basicComponents = components.stream().filter(BasicComponent.class::isInstance).map(BasicComponent.class::cast).collect(Collectors.toList());
		List<ResourceDemandingBehaviour> behaviours = basicComponents.stream()
				.map(BasicComponent::getServiceEffectSpecifications__BasicComponent)
				.flatMap(List::stream).filter(ResourceDemandingBehaviour.class::isInstance).map(ResourceDemandingBehaviour.class::cast)
				.collect(Collectors.toList());
		for(ResourceDemandingBehaviour rdb : behaviours) {
			if(rdb.getId().equals(id)) {
				return rdb;
			}
		}
		return null;
	}
	
	protected static AbstractAction getAbstractAction(String id, ResourceDemandingBehaviour behaviour) {
		List<AbstractAction> actions = behaviour.getSteps_Behaviour();
		for(AbstractAction a : actions) {
			if(a.getId().equals(id)) {
				return a;
			}
		}
		return null;
	}
	
	protected static ScenarioBehaviour getScenarioBehaviour(String id, UsageModel model) {
		List<UsageScenario> scenarios = model.getUsageScenario_UsageModel();
		for (UsageScenario sc : scenarios) {
			if(sc.getScenarioBehaviour_UsageScenario().getId().equals(id)) {
				return sc.getScenarioBehaviour_UsageScenario();
			}
		}
		return null;
	}
	
	protected static AbstractUserAction getAbstractUserAction(String id, ScenarioBehaviour behaviour) {
		List<AbstractUserAction> actions = behaviour.getActions_ScenarioBehaviour();
		for(AbstractUserAction action : actions) {
			if (action.getId().equals(id)) {
				return action;
			}
		}
		return null;
	}
	
}
