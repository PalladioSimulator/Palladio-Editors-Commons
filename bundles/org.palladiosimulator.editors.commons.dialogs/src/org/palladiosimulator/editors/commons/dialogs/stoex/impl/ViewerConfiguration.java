package org.palladiosimulator.editors.commons.dialogs.stoex.impl;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingHelper;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class ViewerConfiguration {
    
    @Inject 
    private IDialogSettings dialogSettings;
    
    @Inject
    private EmbeddedEditorFactory editorFactory;
    
    @Inject
    private HighlightingHelper highlightingHelper;
    
    public IDialogSettings getDialogSettings() {
        return dialogSettings;
    }

    public void setDialogSettings(IDialogSettings dialogSettings) {
        this.dialogSettings = dialogSettings;
    }

    public EmbeddedEditorFactory getEmbeddedEditorFactory() {
        return editorFactory;
    }

    public void setEmbeddedEditorFactory(EmbeddedEditorFactory factory) {
        this.editorFactory = factory;
    }

    public HighlightingHelper getHighlightingHelper() {
        return highlightingHelper;
    }

    public void setHighlightingHelper(HighlightingHelper highlightingHelper) {
        this.highlightingHelper = highlightingHelper;
    }

}
