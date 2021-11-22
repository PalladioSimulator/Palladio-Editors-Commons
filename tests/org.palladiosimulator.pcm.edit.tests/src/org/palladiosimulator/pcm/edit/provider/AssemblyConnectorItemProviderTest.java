package org.palladiosimulator.pcm.edit.provider;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.palladiosimulator.pcm.edit.provider.TestItemProviderUtilities;

import java.util.ArrayList;
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
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.provider.AssemblyConnectorItemProvider;
import org.palladiosimulator.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import org.palladiosimulator.pcm.core.entity.InterfaceProvidingEntity;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.system.SystemFactory;
import org.palladiosimulator.pcm.repository.Repository;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class AssemblyConnectorItemProviderTest {

	@Test
	public void assemblyConnectorItemProviderTest() {
		// init EMF for standalone use

		//Retrieve default Factory Singleton
		CompositionFactory compositionFactory = CompositionFactory.eINSTANCE;
		RepositoryFactory repositoryFactory = RepositoryFactory.eINSTANCE;
		SystemFactory systemFactory = SystemFactory.eINSTANCE;
		
		//create Content of our Model
		System system = systemFactory.createSystem(); // parentStructure / RepositoryStructure
		AssemblyConnector connector1 = compositionFactory.createAssemblyConnector();
		connector1.setParentStructure__Connector(system);
		AssemblyContext providingContext = compositionFactory.createAssemblyContext();
		providingContext.setParentStructure__AssemblyContext(system);
		AssemblyContext requiringContext = compositionFactory.createAssemblyContext();
		requiringContext.setParentStructure__AssemblyContext(system);
		OperationProvidedRole providingRole = repositoryFactory.createOperationProvidedRole();
		providingRole.setProvidingEntity_ProvidedRole(providingContext.getEncapsulatedComponent__AssemblyContext());
		OperationRequiredRole requiringRole = repositoryFactory.createOperationRequiredRole();
		requiringRole.setRequiringEntity_RequiredRole(requiringContext.getEncapsulatedComponent__AssemblyContext());
		//Create expected result list
		Collection<AssemblyContext> expected = new ArrayList<AssemblyContext>();
		expected.add(requiringContext);
		expected.add(providingContext);
		expected.add(null);
		
		CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
		AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(connector1, CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);
		
		assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(connector1);
        assertEquals(actual.size(), 3); //null is in the list always included. 2 AssemblyContexts + null = 3. null is put into list by mdsd-tools in most cases
        assertTrue(expected.containsAll(actual));
	}

	@Test
	public void testAssemblyConnectorItemProvider__addProvidedRole_AssemblyConnectorPropertyDescriptor() {
		System testSystem = TestItemProviderUtilities.loadSystem();
		RepositoryFactory repositoryFactory = RepositoryFactory.eINSTANCE;
		//Repository testRepository = loadRepository();
		AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_hWBckHD5EeSA4fySuX9I2Q", testSystem);
		
		//Create expected result list
		Collection<OperationProvidedRole> expected = new ArrayList<OperationProvidedRole>();
		//Loading Repository somehow is not working => Create OPR with same ID and compare on ID.
		OperationProvidedRole providingRole = repositoryFactory.createOperationProvidedRole();
		providingRole.setId("_MtSiMHDyEeSqnN80MQ2uGw");
		expected.add(null);
		
		//Get result
		CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
		AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector, CompositionPackage.Literals.ASSEMBLY_CONNECTOR__PROVIDED_ROLE_ASSEMBLY_CONNECTOR);
		
		assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
		assertTrue(expected.size() == actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	@Test
	public void testAssemblyConnectorItemProvider__addRequiredRole_AssemblyConnectorPropertyDescriptor() {
		System testSystem = TestItemProviderUtilities.loadSystem();
		RepositoryFactory repositoryFactory = RepositoryFactory.eINSTANCE;
		//Repository testRepository = loadRepository(); geht nicht
		AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_hWBckHD5EeSA4fySuX9I2Q", testSystem);
		
		//Create expected result list
		Collection<OperationRequiredRole> expected = new ArrayList<OperationRequiredRole>();
		//Loading Repository somehow is not working => Create ORR with same ID and compare on ID.
		OperationRequiredRole requiringRole = repositoryFactory.createOperationRequiredRole();
		requiringRole.setId("_cljekHD2EeSA4fySuX9I2Q");
		expected.add(null);
		
		//Get result
		CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
		AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector, CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRED_ROLE_ASSEMBLY_CONNECTOR);
		
		assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	@Test
	public void testAssemblyConnectorItemProvider__addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor() {
		System testSystem = TestItemProviderUtilities.loadSystem();
		AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_qmijsLYdEeapTpBxO5_ZIQ", testSystem);
		List<AssemblyContext> expected = new ArrayList<AssemblyContext>();

		expected.add(TestItemProviderUtilities.getAssemblyContext("_qxAiILg7EeSNPorBlo7x9g", testSystem));
		
		CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
		AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector, CompositionPackage.Literals.ASSEMBLY_CONNECTOR__PROVIDING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);
		
		assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	@Test
	public void testAssemblyConnectorItemProvider__addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor() {
		System testSystem = TestItemProviderUtilities.loadSystem();
		AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_qmijsLYdEeapTpBxO5_ZIQ", testSystem);
		List<AssemblyContext> expected = new ArrayList<AssemblyContext>();
		expected.add(TestItemProviderUtilities.getAssemblyContext("_9eK7YHDrEeSqnN80MQ2uGw", testSystem));
		
		CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
		AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector, CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);
		
		assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
		assertTrue(expected.containsAll(actual));
	}
}
