package org.palladiosimulator.editors.commons.dialogs.stoex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.editors.commons.dialogs.stoex.impl.ViewerConfiguration;

@SuppressWarnings("restriction")
public class ViewerConfigurationTest {

    private ViewerConfiguration subject;

    @BeforeEach
    public void setup() {
        subject = new ViewerConfiguration();
    }

    @Test
    public void testDialogSettings() {
        var mock = mock(IDialogSettings.class);
        subject.setDialogSettings(mock);
        assertEquals(mock, subject.getDialogSettings());
    }

    @Test
    public void testEmbeddedEditorFactory() {
        var mock = mock(EmbeddedEditorFactory.class);
        subject.setEmbeddedEditorFactory(mock);
        assertEquals(mock, subject.getEmbeddedEditorFactory());
    }

    @Test
    public void testHighlightingHelper() {
        var mock = mock(HighlightingHelper.class);
        subject.setHighlightingHelper(mock);
        assertEquals(mock, subject.getHighlightingHelper());
    }

}
