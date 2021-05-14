package org.palladiosimulator.editors.commons.tabs.parameters;

import java.io.NotSerializableException;

import org.palladiosimulator.editors.commons.tabs.PCMBenchTabsActivator;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.stoex.api.StoExSerialiser;

/**
 * Utility class for serialising (parts of) {@link VariableUsage} elements.
 */
public final class VariableUsageSerialiser {

    private static final StoExSerialiser STOEX_SERIALISER = StoExSerialiser.createInstance();

    private VariableUsageSerialiser() {
        // intentionally left blank
    }

    /**
     * Serialise the variable name specified by the {@link VariableUsage}. This consists of the
     * variable name and the characterisation type.
     * 
     * @param variableUsage
     *            The variable usage holding the name to be serialised.
     * @return The serialised variable name.
     */
    public static String serialiseVariableName(VariableUsage variableUsage) {
        String result = "<not set yet>";
        if (variableUsage.getNamedReference__VariableUsage() != null) {
            try {
                result = STOEX_SERIALISER.serialise(variableUsage.getNamedReference__VariableUsage());
            } catch (NotSerializableException e) {
                // only log error and continue
                PCMBenchTabsActivator.getDefault()
                    .getLog()
                    .error("Error while serialising reference name.", e);
            }
        }

        if (variableUsage.getVariableCharacterisation_VariableUsage()
            .size() > 0) {
            VariableCharacterisation firstCharacterisation = (VariableCharacterisation) variableUsage
                .getVariableCharacterisation_VariableUsage()
                .get(0);
            result += ".";
            result += firstCharacterisation.getType()
                .getLiteral();
        } else {
            result += ".<missing characterisation>";
        }

        return result;
    }

}
