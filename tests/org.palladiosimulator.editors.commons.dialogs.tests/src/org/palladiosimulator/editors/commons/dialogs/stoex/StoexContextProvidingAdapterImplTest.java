package org.palladiosimulator.editors.commons.dialogs.stoex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.eclipse.emf.common.notify.Notifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.commons.stoex.services.StoexContext;
import org.palladiosimulator.commons.stoex.services.StoexContextProvider;
import org.palladiosimulator.commons.stoex.services.StoexContextProviderImpl;
import org.palladiosimulator.editors.commons.dialogs.stoex.impl.StoexContextProvidingAdapterImpl;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class StoexContextProvidingAdapterImplTest {

    private StoexContextProvidingAdapterImpl subject;

    @BeforeEach
    public void setup() {
        this.subject = new StoexContextProvidingAdapterImpl();
    }

    @Test
    public void testSetTarget() {
        Notifier expectedTarget = mock(Notifier.class);
        subject.setTarget(expectedTarget);
        Notifier actualTarget = subject.getTarget();
        assertEquals(expectedTarget, actualTarget);
    }

    @Test
    public void testIsAdapterFor() {
        assertTrue(subject.isAdapterForType(StoexContextProvider.class));
        assertFalse(subject.isAdapterForType(StoexContextProviderImpl.class));
    }

    @Test
    public void testGetEmptyContext() {
        StoexContext context = subject.getContext(null);
        assertEquals(Optional.empty(), context.getContainer());
        assertEquals(Optional.empty(), context.getExpectedType());
    }

    @Test
    public void testSetContainer() {
        RandomVariable expectedContainer = mock(RandomVariable.class);
        subject.setStoexContainer(expectedContainer);

        StoexContext context = subject.getContext(null);
        assertEquals(Optional.of(expectedContainer), context.getContainer());
        assertEquals(Optional.empty(), context.getExpectedType());
    }

    @Test
    public void testSetExpectedType() {
        TypeEnum expectedType = TypeEnum.AUX_FUNCTION;
        subject.setExpectedType(expectedType);

        StoexContext context = subject.getContext(null);
        assertEquals(Optional.empty(), context.getContainer());
        assertEquals(Optional.of(expectedType), context.getExpectedType());
    }
}
