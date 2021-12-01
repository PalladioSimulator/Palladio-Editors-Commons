package org.palladiosimulator.pcm.edit.provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.DelegationConnector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import org.palladiosimulator.pcm.core.composition.provider.ProvidedDelegationConnectorItemProvider;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.system.System;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)

public class ProvidedDelegationConnectorItemProviderTest {

    // Problem bei diesem Test, gleich wie bei roles von assemblyconnectortest: Repository wird
    // nicht geladen, deshalb hat der Connector vermeintlich keine Roles.
    @Disabled
    @Test
    public void testProvidedDelegationConnectorItemProvideraddInnerProvidedRoleProvidedDelegationConnectorPropertyDescriptor() {
        System testSystem = TestItemProviderUtilities.loadSystem();
        RepositoryFactory repositoryFactory = RepositoryFactory.eINSTANCE;
        // Repository testRepository = loadRepository();
        DelegationConnector connector1 = TestItemProviderUtilities.getDelegationConnector("_wVTYoJelEeajsMFpiDxCKw",
                testSystem);
        ProvidedDelegationConnector testConnector = (ProvidedDelegationConnector) connector1;
        assertNotNull(testConnector);

        // Create expected result list
        Collection<OperationProvidedRole> expected = new ArrayList<OperationProvidedRole>();
        // Loading Repository somehow is not working => Create OPR with same ID and compare on ID.
        OperationProvidedRole providingRole = repositoryFactory.createOperationProvidedRole();
        providingRole.setId("_frEeAHD2EeSA4fySuX9I2Q");
        expected.add(providingRole);
        expected.add(null);
        testConnector.setInnerProvidedRole_ProvidedDelegationConnector(providingRole);

        // Get result
        CompositionItemProviderAdapterFactory adapterFactory = new CompositionItemProviderAdapterFactory();
        ProvidedDelegationConnectorItemProvider provider = new ProvidedDelegationConnectorItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testConnector,
                CompositionPackage.Literals.PROVIDED_DELEGATION_CONNECTOR__INNER_PROVIDED_ROLE_PROVIDED_DELEGATION_CONNECTOR);

        assertNotNull(descriptor);
        var actual = descriptor.getChoiceOfValues(testConnector);
        assertNotNull(actual.size());
        java.lang.System.out.println(expected.size());
        java.lang.System.out.println(actual.size());
        assertTrue(expected.size() == actual.size());
        assertTrue(expected.containsAll(actual));
    }

}
