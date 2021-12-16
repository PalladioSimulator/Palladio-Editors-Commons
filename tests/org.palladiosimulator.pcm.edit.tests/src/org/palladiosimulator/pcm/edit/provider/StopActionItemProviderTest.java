package org.palladiosimulator.pcm.edit.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.StopAction;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.StopActionItemProvider;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class StopActionItemProviderTest {
    private Repository testRepository;

    @BeforeEach
    public void setUp() {
        testRepository = TestItemProviderUtilities.loadRepository();
    }

    @Test
    public void addSuccessorStopActionPropertyDescriptorTest() {
        ResourceDemandingBehaviour rdb = TestItemProviderUtilities
            .getResourceDemandingBehaviour("_frGTMHD2EeSA4fySuX9I2Q", testRepository);
        StopAction testAction = (StopAction) TestItemProviderUtilities.getAbstractAction("_frG6QXD2EeSA4fySuX9I2Q",
                rdb); // Is a StopAction
        // define expected Result - No Succesors for stop so only list with null
        List<AbstractAction> expected = new ArrayList<AbstractAction>();
        expected.add(null);

        // Get result
        SeffItemProviderAdapterFactory adapterFactory = new SeffItemProviderAdapterFactory();
        StopActionItemProvider provider = new StopActionItemProvider(adapterFactory);

        IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction,
                SeffPackage.Literals.ABSTRACT_ACTION__SUCCESSOR_ABSTRACT_ACTION);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);

        assertNotNull(descriptor);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }
}
