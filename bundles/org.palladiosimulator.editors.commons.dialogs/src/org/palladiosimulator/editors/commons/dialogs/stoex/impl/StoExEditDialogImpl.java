package org.palladiosimulator.editors.commons.dialogs.stoex.impl;

import java.util.Collection;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.palladiosimulator.editors.commons.dialogs.stoex.StoExEditDialog;
import org.palladiosimulator.pcm.stoex.parser.antlr.PCMStoexParser;

import com.google.inject.Inject;

import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

@SuppressWarnings("restriction")
public class StoExEditDialogImpl extends TitleAreaDialog implements StoExEditDialog {

    @Inject
    private ViewerConfiguration configuration;

    @Inject
    private EmbeddedEditorFactory embeddedEditorFactory;

    @Inject
    private IResourceValidator originalValidator;

    @Inject
    private IEditedResourceProvider editedResourceProvider;

    @Inject
    private ISerializer serializer;

    @Inject
    private PCMStoexParser parser;

    private final StoexContextProvidingAdapterImpl contextProvider = new StoexContextProvidingAdapterImpl();

    private EmbeddedEditorModelAccess modelAccess;

    private Expression initialExpression;

    private Expression result = null;

    private String resultString = null;

    private String title = "Edit a stochastic expression";

    public StoExEditDialogImpl(Shell parentShell, TypeEnum expectedType) {
        super(parentShell);
        contextProvider.setExpectedType(expectedType);
        setHelpAvailable(true);
    }

    @Override
    public void setInitialExpression(Expression expression) {
        this.initialExpression = expression;
    }

    @Override
    public void setDisplayTitle(String dialogMessage) {
        title = dialogMessage;
    }

    @Override
    public Expression getResult() {
        return result;
    }

    @Override
    public String getResultString() {
        return resultString;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        PlatformUI.getWorkbench()
            .getHelpSystem()
            .setHelp(parent, "org.palladiosimulator.pcm.help.stoexdialog");
        final var composite = (Composite) super.createDialogArea(parent);
        final var observableValidator = new ObservableResourceValidator(originalValidator);
        final var embeddedEditor = embeddedEditorFactory.newEditor(editedResourceProvider)
            .showErrorAndWarningAnnotations()
            .withResourceValidator(observableValidator)
            .withParent(composite);
        modelAccess = embeddedEditor.createPartialEditor();
        configuration.getHighlightingHelper()
            .install(embeddedEditor.getConfiguration(), embeddedEditor.getViewer());
        embeddedEditor.getViewer()
            .getTextWidget()
            .setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));
        embeddedEditor.getDocument()
            .set(serialize(initialExpression));
        observableValidator.addValidationListener(this::processValidationResult);
        embeddedEditor.getDocument()
            .readOnly(resource -> {
                resource.eAdapters()
                    .add(contextProvider);
                return contextProvider;
            });
        composite.pack();
        return composite;
    }

    protected String serialize(Expression expression) {
        if (expression == null) {
            return "";
        }
        var r = editedResourceProvider.createResource();
        var copy = EcoreUtil.copy(expression);
        r.getContents()
            .add(copy);
        return serializer.serialize(copy);
    }

    @Override
    protected Control createContents(Composite parent) {
        Control control = super.createContents(parent);
        getShell().setText("Edit stochastic expression");
        setTitle(title);
        return control;
    }

    @Override
    protected boolean isResizable() {
        return true;
    }

    @Override
    protected void okPressed() {
        setResultVariables();
        super.okPressed();
    }

    protected void setResultVariables() {
        var resultText = modelAccess.getSerializedModel();
        var parseResult = parser.doParse(resultText);
        if (!parseResult.hasSyntaxErrors()) {
            result = (Expression) parseResult.getRootASTElement();
            resultString = serialize(result);
        }
    }

    protected void processValidationResult(final Collection<Issue> issues) {
        Display.getDefault()
            .asyncExec(() -> {
                final var okButton = getButton(IDialogConstants.OK_ID);
                if (issues.isEmpty()) {
                    okButton.setEnabled(true);
                } else {
                    okButton.setEnabled(false);
                }
            });
    }

    @Override
    public void setContext(RandomVariable context) {
        contextProvider.setStoexContainer(context);
    }

}
