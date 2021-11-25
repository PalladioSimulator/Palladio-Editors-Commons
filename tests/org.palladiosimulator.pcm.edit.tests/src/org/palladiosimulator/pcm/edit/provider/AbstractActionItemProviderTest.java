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
import org.palladiosimulator.pcm.seff.provider.AbstractActionItemProvider;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;

import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
public class AbstractActionItemProviderTest {
	
	private Repository testRepository;
	
	
	@BeforeEach
	public void setUp() {
		testRepository = TestItemProviderUtilities.loadRepository();
	}
	
	@Test
	public void addSuccessor_AbstractActionPropertyDescriptorTest() {
		ResourceDemandingBehaviour rdb = TestItemProviderUtilities.getResourceDemandingBehaviour("_frGTMHD2EeSA4fySuX9I2Q", testRepository);
		AbstractAction testAction = TestItemProviderUtilities.getAbstractAction("_8e4gcLzNEeSAHuL4ItXOLQ", rdb);
		//define expected Result
		AbstractAction stopAction = TestItemProviderUtilities.getAbstractAction("_frG6QXD2EeSA4fySuX9I2Q", rdb);
		AbstractAction extCallAction = TestItemProviderUtilities.getAbstractAction("_mqKtIHQiEeSabL89DKH5bQ", rdb);
		
		List<AbstractAction> expected = new ArrayList<AbstractAction>();
		expected.add(null);
		expected.add(extCallAction);
		expected.add(stopAction);
		
		//Get result
		SeffItemProviderAdapterFactory adapterFactory = new SeffItemProviderAdapterFactory();
		AbstractActionItemProvider provider = new AbstractActionItemProvider(adapterFactory);
		
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction, SeffPackage.Literals.ABSTRACT_ACTION__SUCCESSOR_ABSTRACT_ACTION);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);

		
		assertNotNull(descriptor);
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	@Test
	public void addPredecessor_AbstractActionPropertyDescriptorTest() {
		ResourceDemandingBehaviour rdb = TestItemProviderUtilities.getResourceDemandingBehaviour("_frGTMHD2EeSA4fySuX9I2Q", testRepository);
		AbstractAction testAction = TestItemProviderUtilities.getAbstractAction("_8e4gcLzNEeSAHuL4ItXOLQ", rdb);
		//define expected Result
		AbstractAction startAction = TestItemProviderUtilities.getAbstractAction("_frG6QHD2EeSA4fySuX9I2Q", rdb);
		AbstractAction extCallAction = TestItemProviderUtilities.getAbstractAction("_mqKtIHQiEeSabL89DKH5bQ", rdb);
		
		List<AbstractAction> expected = new ArrayList<AbstractAction>();
		expected.add(null);
		expected.add(extCallAction);
		expected.add(startAction);
		
		//Get result
		SeffItemProviderAdapterFactory adapterFactory = new SeffItemProviderAdapterFactory();
		AbstractActionItemProvider provider = new AbstractActionItemProvider(adapterFactory);
		
		
		IItemPropertyDescriptor descriptor = provider.getPropertyDescriptor(testAction, SeffPackage.Literals.ABSTRACT_ACTION__PREDECESSOR_ABSTRACT_ACTION);
        Collection<?> actual = descriptor.getChoiceOfValues(testAction);

		
		assertNotNull(descriptor);
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	
}
