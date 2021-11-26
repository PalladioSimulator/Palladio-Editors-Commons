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
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.provider.AbstractActionItemProvider;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.ScenarioBehaviour;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.provider.AbstractUserActionItemProvider;
import org.palladiosimulator.pcm.usagemodel.provider.UsagemodelItemProviderAdapterFactory;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class AbstractUserActionItemProviderTest {

	private UsageModel testUsageModel;
	
	@BeforeEach
	public void setUp() {
		testUsageModel = TestItemProviderUtilities.loadUsageModel();
	}
	
	@Test
	public void addSuccessor_AbstractUserActionPropertyDescriptorTest() {
		ScenarioBehaviour scenario = TestItemProviderUtilities.getScenarioBehaviour("__hrGYHD6EeSA4fySuX9I2Q", testUsageModel);
		AbstractUserAction testAction = TestItemProviderUtilities.getAbstractUserAction("_TutY0Hk9EeSGtbTG2d9Ipg", scenario);
		
		//define expected result
		AbstractUserAction stopAction = TestItemProviderUtilities.getAbstractUserAction("__huJsHD6EeSA4fySuX9I2Q", scenario);
		List<AbstractUserAction> expected = new ArrayList<AbstractUserAction>();
		expected.add(null);
		expected.add(stopAction);
		
		//get result
		UsagemodelItemProviderAdapterFactory adapterFactory = new UsagemodelItemProviderAdapterFactory();
		AbstractUserActionItemProvider provider = new AbstractUserActionItemProvider(adapterFactory);
		
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction, UsagemodelPackage.Literals.ABSTRACT_USER_ACTION__SUCCESSOR);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);
        
        assertNotNull(descriptor);
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	@Test
	public void addPredecessor_AbstractUserActionPropertyDescriptorTest() {
		ScenarioBehaviour scenario = TestItemProviderUtilities.getScenarioBehaviour("__hrGYHD6EeSA4fySuX9I2Q", testUsageModel);
		AbstractUserAction testAction = TestItemProviderUtilities.getAbstractUserAction("_TutY0Hk9EeSGtbTG2d9Ipg", scenario);
		
		//define expected result
		AbstractUserAction startAction = TestItemProviderUtilities.getAbstractUserAction("__hs7kHD6EeSA4fySuX9I2Q", scenario);
		List<AbstractUserAction> expected = new ArrayList<AbstractUserAction>();
		expected.add(null);
		expected.add(startAction);
		
		//get result
		UsagemodelItemProviderAdapterFactory adapterFactory = new UsagemodelItemProviderAdapterFactory();
		AbstractUserActionItemProvider provider = new AbstractUserActionItemProvider(adapterFactory);
		
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction, UsagemodelPackage.Literals.ABSTRACT_USER_ACTION__PREDECESSOR);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);
        
        assertNotNull(descriptor);
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
}
