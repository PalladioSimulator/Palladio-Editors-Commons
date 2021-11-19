package org.palladiosimulator.pcm.edit.provider;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.provider.AssemblyConnectorItemProvider;
import org.palladiosimulator.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationRequiredRole;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.system.SystemFactory;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class ModelTest {

	
	@Test
	public void assemblyConnectorTest() {
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
		
		CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
		AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(connector1, CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);
		
		assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(connector1);
        java.lang.System.out.println(actual.size());
        assertEquals(actual.size(), 3); //null is in the list always included. 2 AssemblyContexts + null = 3. null is put into list by mdsd-tools in most cases
	}
	
	
}
