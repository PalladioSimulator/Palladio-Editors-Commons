package org.palladiosimulator.pcm.edit.provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.DelegationConnector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import org.palladiosimulator.pcm.core.composition.provider.ProvidedDelegationConnectorItemProvider;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.system.System;

import de.uka.ipd.sdq.identifier.Identifier;
import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)

public class ProvidedDelegationConnectorItemProviderTest {

    @Test
    public void testProvidedDelegationConnectorItemProvideraddInnerProvidedRoleProvidedDelegationConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        Repository testRepository = TestItemProviderUtilities.loadRepository();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_wVTYoJelEeajsMFpiDxCKw",
                testSystem);
        ProvidedDelegationConnector testConnector = (ProvidedDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<ProvidedRole> expected = new ArrayList<ProvidedRole>();
        ProvidedRole providedRole =
        		TestItemProviderUtilities.getProvidedRole("_frEeAHD2EeSA4fySuX9I2Q", testRepository);
        expected.add(null);
        expected.add(providedRole);
        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        ProvidedDelegationConnectorItemProvider provider = new ProvidedDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.PROVIDED_DELEGATION_CONNECTOR__INNER_PROVIDED_ROLE_PROVIDED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(actual.get(1).getId().equals(providedRole.getId()));
    }
    
    @Test
    public void testProvidedDelegationConnectorItemProvideraddOuterProvidedRoleProvidedDelegationConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_wVTYoJelEeajsMFpiDxCKw",
                testSystem);
        ProvidedDelegationConnector testConnector = (ProvidedDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<ProvidedRole> expected = new ArrayList<ProvidedRole>();
        ProvidedRole providedRole =
        		TestItemProviderUtilities.getOuterProvidedRole("_b9NvYHD6EeSA4fySuX9I2S", testSystem);
        expected.add(null);
        expected.add(providedRole);
        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        ProvidedDelegationConnectorItemProvider provider = new ProvidedDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.PROVIDED_DELEGATION_CONNECTOR__OUTER_PROVIDED_ROLE_PROVIDED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(actual.get(1).getId().equals(providedRole.getId()));
    }
    
    @Test
    public void testProvidedDelegationConnectorItemProvideraddAssemblyContextProvidedDelegationConnectorPropertyDescriptor() {
    	System testSystem = TestItemProviderUtilities.loadSystem();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_wVTYoJelEeajsMFpiDxCKw",
                testSystem);
        ProvidedDelegationConnector testConnector = (ProvidedDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<AssemblyContext> expected = new ArrayList<AssemblyContext>();
        AssemblyContext assemblyMediaManagement = TestItemProviderUtilities.getAssemblyContext("_bm3CcAjmEeyy5MM7BmZ05A", testSystem);
        AssemblyContext mediaManagement = TestItemProviderUtilities.getAssemblyContext("_C1kK8HD5EeSA4fySuX9I2Z", testSystem);
        expected.add(null);
        expected.add(assemblyMediaManagement);
        expected.add(mediaManagement);
        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        ProvidedDelegationConnectorItemProvider provider = new ProvidedDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.PROVIDED_DELEGATION_CONNECTOR__ASSEMBLY_CONTEXT_PROVIDED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = (List<Identifier>) descriptor.getChoiceOfValues(testConnector);
        assertTrue(expected.size() == actual.size());
        assertTrue(expected.containsAll(actual));
    }
    

}
