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
import org.palladiosimulator.pcm.seff.StartAction;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.StartActionItemProvider;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class StartActionItemProviderTest {
	
	private Repository testRepository;

	
	@BeforeEach
	public void setUp() {
		testRepository = TestItemProviderUtilities.loadRepository();
	}
	
	@Test
	public void addPredecessor_StartActionPropertyDescriptorTest() {
		ResourceDemandingBehaviour rdb = TestItemProviderUtilities.getResourceDemandingBehaviour("_frGTMHD2EeSA4fySuX9I2Q", testRepository);
		StartAction testAction = (StartAction) TestItemProviderUtilities.getAbstractAction("_frG6QHD2EeSA4fySuX9I2Q", rdb); // Is a StartAction
		//define expected Result - No Predecessors for start so only list with null
		List<AbstractAction> expected = new ArrayList<AbstractAction>();
		expected.add(null);
		
		//Get result
		SeffItemProviderAdapterFactory adapterFactory = new SeffItemProviderAdapterFactory();
		StartActionItemProvider provider = new StartActionItemProvider(adapterFactory);
		
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction, SeffPackage.Literals.ABSTRACT_ACTION__PREDECESSOR_ABSTRACT_ACTION);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);

		
		assertNotNull(descriptor);
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
}
