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
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.Start;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.provider.AbstractUserActionItemProvider;
import org.palladiosimulator.pcm.usagemodel.provider.StartItemProvider;
import org.palladiosimulator.pcm.usagemodel.provider.UsagemodelItemProviderAdapterFactory;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class StartItemProviderTest {
	
private UsageModel testUsageModel;
	
	@BeforeEach
	public void setUp() {
		testUsageModel = TestItemProviderUtilities.loadUsageModel();
	}
	
	@Test
	public void addPredecessor_StartPropertyDescriptorTest() {
		ScenarioBehaviour scenario = TestItemProviderUtilities.getScenarioBehaviour("__hrGYHD6EeSA4fySuX9I2Q", testUsageModel);
		Start testAction = (Start) TestItemProviderUtilities.getAbstractUserAction("__hs7kHD6EeSA4fySuX9I2Q", scenario);
		
		//define expected result - only null, as Start has no predecessors.
		List<AbstractUserAction> expected = new ArrayList<AbstractUserAction>();
		expected.add(null);
		
		//get result
		UsagemodelItemProviderAdapterFactory adapterFactory = new UsagemodelItemProviderAdapterFactory();
		StartItemProvider provider = new StartItemProvider(adapterFactory);
		
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction, UsagemodelPackage.Literals.ABSTRACT_USER_ACTION__PREDECESSOR);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);
        
        assertNotNull(descriptor);
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
}
