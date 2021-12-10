package org.palladiosimulator.pcm.edit.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.jupiter.api.Disabled;
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
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.system.SystemFactory;

import de.uka.ipd.sdq.identifier.Identifier;
import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension; 

@ExtendWith(PlatformStandaloneExtension.class)
public class AssemblyConnectorItemProviderTest {

    @Test
    public void assemblyConnectorItemProviderTest() {
        // init EMF for standalone use

        // Retrieve default Factory Singleton
        CompositionFactory compositionFactory = CompositionFactory.eINSTANCE;
        RepositoryFactory repositoryFactory = RepositoryFactory.eINSTANCE;
        SystemFactory systemFactory = SystemFactory.eINSTANCE;

        // create Content of our Model
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
        // Create expected result list
        Collection<AssemblyContext> expected = new ArrayList<AssemblyContext>();
        expected.add(requiringContext);
        expected.add(providingContext);
        expected.add(null);

        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(connector1,
                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);

        assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(connector1);
        assertEquals(actual.size(), 3); // null is in the list always included. 2 AssemblyContexts +
                                        // null = 3. null is put into list by mdsd-tools in most
                                        // cases
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void testAssemblyConnectorItemProvideraddProvidedRoleAssemblyConnectorPropertyDescriptor() {
    	System testSystem = TestItemProviderUtilities.loadSystem();
        Repository testRepository = TestItemProviderUtilities.loadRepository();
        AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_hWBckHD5EeSA4fySuX9I2Q",
                        testSystem);
        // Create expected result list
        Collection<ProvidedRole> expected = new ArrayList<ProvidedRole>();
        ProvidedRole providedRole =
        		TestItemProviderUtilities.getProvidedRole("_MtSiMHDyEeSqnN80MQ2uGw", testRepository);
        expected.add(null);
        expected.add(providedRole);
        
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__PROVIDED_ROLE_ASSEMBLY_CONNECTOR);
        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(actual.get(1).getId().equals(providedRole.getId()));
    }

    @Test
    public void testAssemblyConnectorItemProvideraddRequiredRoleAssemblyConnectorPropertyDescriptor() {
    	System testSystem = TestItemProviderUtilities.loadSystem();
        Repository testRepository = TestItemProviderUtilities.loadRepository();
        AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_hWBckHD5EeSA4fySuX9I2Q",
                        testSystem);
        // Create expected result list
        Collection<RequiredRole> expected = new ArrayList<RequiredRole>();
        RequiredRole requiredRole =
        		TestItemProviderUtilities.getRequiredRole("_cljekHD2EeSA4fySuX9I2Q", testRepository);
        expected.add(null);
        expected.add(requiredRole);
        
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRED_ROLE_ASSEMBLY_CONNECTOR);
        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(actual.get(1).getId().equals(requiredRole.getId()));
    }

    @Test
    public void testAssemblyConnectorItemProvideraddProvidingAssemblyContextAssemblyConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_hWBckHD5EeSA4fySuX9I2Q",
                testSystem);
        List<AssemblyContext> expected = new ArrayList<AssemblyContext>();
        expected.add(null);
        expected.add(TestItemProviderUtilities.getAssemblyContext("_IPnY0HD5EeSA4fySuX9I2S", testSystem));

        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__PROVIDING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);

        assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void testAssemblyConnectorItemProvideraddRequiringAssemblyContextAssemblyConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        AssemblyConnector testConnector = TestItemProviderUtilities.getAssemblyConnector("_hWBckHD5EeSA4fySuX9I2Q",
                testSystem);
        List<AssemblyContext> expected = new ArrayList<AssemblyContext>();
        expected.add(null);
        expected.add(TestItemProviderUtilities.getAssemblyContext("_C1kK8HD5EeSA4fySuX9I2Z", testSystem));
        expected.add(TestItemProviderUtilities.getAssemblyContext("_bm3CcAjmEeyy5MM7BmZ05A", testSystem));

        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        AssemblyConnectorItemProvider provider = new AssemblyConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR);

        assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(expected.containsAll(actual));
    }
}
