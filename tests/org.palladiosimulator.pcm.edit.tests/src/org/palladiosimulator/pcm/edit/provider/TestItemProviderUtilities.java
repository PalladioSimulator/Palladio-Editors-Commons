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
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.system.System;

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
	
	// Funktioniert nicht, NoClassDefFoundError org/apache/log4j/Logger.
	//Bei loadSystem funktioniert iwie alles. KP...
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
	
	
	
	
	protected static AssemblyConnector getAssemblyConnector(String id, System system) {
		List<Connector> connectors = system.getConnectors__ComposedStructure();
		for (Connector c : connectors) {
			if (c instanceof AssemblyConnector && c.getId().equals(id)) {
				return (AssemblyConnector) c;
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
	
	
	
}
