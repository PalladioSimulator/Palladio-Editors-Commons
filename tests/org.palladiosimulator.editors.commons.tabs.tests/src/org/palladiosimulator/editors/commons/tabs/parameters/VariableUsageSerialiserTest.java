package org.palladiosimulator.editors.commons.tabs.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.pcm.parameter.ParameterFactory;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableCharacterisationType;
import org.palladiosimulator.pcm.parameter.VariableUsage;

import de.uka.ipd.sdq.stoex.StoexFactory;
import de.uka.ipd.sdq.stoex.VariableReference;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;
import tools.mdsd.library.standalone.initialization.StandaloneInitializationException;
import tools.mdsd.library.standalone.initialization.StandaloneInitializerBuilder;

public class VariableUsageSerialiserTest {

    @BeforeAll
    public static void init() throws StandaloneInitializationException {
        StandaloneInitializerBuilder.builder()
            .build()
            .init();
    }

    @Test
    public void testEmptyVariableCharacterisation() {
        VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
        assertExpected(variableUsage, "<not set yet>.<missing characterisation>");
    }

    @Test
    public void testEmptyCharacterisation() {
        VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
        VariableReference name = StoexFactory.eINSTANCE.createVariableReference();
        name.setReferenceName("foo");
        variableUsage.setNamedReference__VariableUsage(name);
        assertExpected(variableUsage, "foo.<missing characterisation>");
    }

    @Test
    public void testFullCharacterisation() {
        VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
        VariableReference name = StoexFactory.eINSTANCE.createVariableReference();
        name.setReferenceName("foo");
        variableUsage.setNamedReference__VariableUsage(name);
        VariableCharacterisation characterisation = ParameterFactory.eINSTANCE.createVariableCharacterisation();
        characterisation.setType(VariableCharacterisationType.VALUE);
        variableUsage.getVariableCharacterisation_VariableUsage()
            .add(characterisation);
        assertExpected(variableUsage, "foo.VALUE");
    }

    @Test
    @PluginTestOnly
    public void testInvalidReferenceName() {
        VariableUsage variableUsage = ParameterFactory.eINSTANCE.createVariableUsage();
        VariableReference name = StoexFactory.eINSTANCE.createVariableReference();
        name.setReferenceName("fo.o");
        variableUsage.setNamedReference__VariableUsage(name);
        assertExpected(variableUsage, "<not set yet>.<missing characterisation>");
    }

    protected void assertExpected(VariableUsage variableUsage, String expected) {
        String actual = VariableUsageSerialiser.serialiseVariableName(variableUsage);
        assertEquals(expected, actual);
    }
}
