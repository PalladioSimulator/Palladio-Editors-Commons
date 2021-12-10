package org.palladiosimulator.pcm.edit.provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.DelegationConnector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import org.palladiosimulator.pcm.core.composition.provider.ProvidedDelegationConnectorItemProvider;
import org.palladiosimulator.pcm.core.composition.provider.RequiredDelegationConnectorItemProvider;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.system.System;

import de.uka.ipd.sdq.identifier.Identifier;
import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)


public class RequiredDelegationConnectorItemProviderTest {

	@Test
    public void testRequiredDelegationConnectorItemProvideraddInnerRequiredRoleRequiredDelegationConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        Repository testRepository = TestItemProviderUtilities.loadRepository();
        RepositoryFactory repositoryFactory = RepositoryFactory.eINSTANCE;
        // Repository testRepository = loadRepository();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_6AQ2YFliEeyJ8NxP0zEPRw",
                testSystem);
        RequiredDelegationConnector testConnector = (RequiredDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<RequiredRole> expected = new ArrayList<RequiredRole>();
        RequiredRole requiredRole =
        		TestItemProviderUtilities.getRequiredRole("_5VbJoGrUEeajAsHLADMRRw", testRepository);
        expected.add(null);
        expected.add(requiredRole);
        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        RequiredDelegationConnectorItemProvider provider = new RequiredDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.REQUIRED_DELEGATION_CONNECTOR__INNER_REQUIRED_ROLE_REQUIRED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(actual.get(1).getId().equals(requiredRole.getId()));
    }
	
	@Test
    public void testRequiredDelegationConnectorItemProvideraddOuterRequiredRoleRequiredDelegationConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_6AQ2YFliEeyJ8NxP0zEPRw",
                testSystem);
        RequiredDelegationConnector testConnector = (RequiredDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<RequiredRole> expected = new ArrayList<RequiredRole>();
        RequiredRole requiredRole =
        		TestItemProviderUtilities.getOuterRequiredRole("_v2fKE1liEeyJ8NxP0zEPRw", testSystem);
        expected.add(null);
        expected.add(requiredRole);
        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        RequiredDelegationConnectorItemProvider provider = new RequiredDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.REQUIRED_DELEGATION_CONNECTOR__OUTER_REQUIRED_ROLE_REQUIRED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(actual.get(1).getId().equals(requiredRole.getId()));
    }
	
	@Test
    public void testRequiredDelegationConnectorItemProvideraddAssemblyContextRequiredDelegationConnectorPropertyDescriptor() {
    	System testSystem = TestItemProviderUtilities.loadSystem();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_6AQ2YFliEeyJ8NxP0zEPRw",
                testSystem);
        RequiredDelegationConnector testConnector = (RequiredDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<AssemblyContext> expected = new ArrayList<AssemblyContext>();
        AssemblyContext instantDownloadCacheContext = TestItemProviderUtilities.getAssemblyContext("_IulGALW1EeapTpBxO5_ZIS", testSystem);
        expected.add(null);
        expected.add(instantDownloadCacheContext);
        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        RequiredDelegationConnectorItemProvider provider = new RequiredDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.REQUIRED_DELEGATION_CONNECTOR__ASSEMBLY_CONTEXT_REQUIRED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(expected.containsAll(actual));
    }
	
	
}
