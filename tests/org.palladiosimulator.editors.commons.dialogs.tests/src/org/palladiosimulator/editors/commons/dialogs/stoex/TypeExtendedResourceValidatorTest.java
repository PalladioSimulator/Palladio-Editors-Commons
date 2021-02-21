package org.palladiosimulator.editors.commons.dialogs.stoex;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.editors.commons.dialogs.stoex.impl.TypeExtendedResourceValidator;

import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.StoexFactory;
import de.uka.ipd.sdq.stoex.analyser.visitors.ExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;
import tools.mdsd.library.standalone.initialization.StandaloneInitializationException;
import tools.mdsd.library.standalone.initialization.StandaloneInitializerBuilder;

public class TypeExtendedResourceValidatorTest {

    private TypeExtendedResourceValidator subject;
    private IResourceValidator validatorMock;
    private ExpressionInferTypeVisitor inferTypeVisitor;
    private Resource resource;

    private static class SimpleIssue implements Issue {

        private final String message;
        private final boolean isSyntaxError;

        public SimpleIssue(String message, boolean isSyntaxError) {
            this.message = message;
            this.isSyntaxError = isSyntaxError;
        }

        @Override
        public Severity getSeverity() {
            return Severity.WARNING;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public String getCode() {
            return "TEST";
        }

        @Override
        public CheckType getType() {
            return CheckType.NORMAL;
        }

        @Override
        public URI getUriToProblem() {
            return null;
        }

        @Override
        public Integer getLineNumber() {
            return 0;
        }

        @Override
        public Integer getLineNumberEnd() {
            return 0;
        }

        @Override
        public Integer getColumn() {
            return 0;
        }

        @Override
        public Integer getColumnEnd() {
            return 0;
        }

        @Override
        public Integer getOffset() {
            return 0;
        }

        @Override
        public Integer getLength() {
            return 0;
        }

        @Override
        public boolean isSyntaxError() {
            return isSyntaxError;
        }

        @Override
        public String[] getData() {
            return new String[0];
        }

    }

    @BeforeAll
    public static void init() throws StandaloneInitializationException {
        StandaloneInitializerBuilder.builder()
            .build()
            .init();
    }

    @BeforeEach
    public void setup() {
        validatorMock = mock(IResourceValidator.class);
        inferTypeVisitor = mock(ExpressionInferTypeVisitor.class);
        subject = new TypeExtendedResourceValidator(validatorMock, inferTypeVisitor, TypeEnum.INT);
        resource = new ResourceSetImpl().createResource(URI.createURI("virtual:/" + UUID.randomUUID()
            .toString() + ".xmi"));
        var expression = StoexFactory.eINSTANCE.createIntLiteral();
        expression.setValue(2);
        resource.getContents()
            .add(expression);
    }

    @Test
    public void testNoViolations() {
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Collections.emptyList());
        when(inferTypeVisitor.getType((Expression) resource.getContents()
            .get(0))).thenReturn(TypeEnum.ANY);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        assertThat(issues, is(empty()));
    }

    @Test
    public void testOriginalViolations() {
        final var originalIssue = new SimpleIssue("TEST", true);
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Arrays.asList(originalIssue));
        when(inferTypeVisitor.getType((Expression) resource.getContents()
            .get(0))).thenReturn(TypeEnum.ANY);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        assertThat(issues.size(), is(1));
        assertThat(issues, hasItem(originalIssue));
    }

    @Test
    public void testTypeMismatch() {
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Collections.emptyList());
        when(inferTypeVisitor.getType((Expression) resource.getContents()
            .get(0))).thenReturn(TypeEnum.BOOL);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        assertThat(issues.size(), is(1));
    }

    @Test
    public void testValidationListenerWithoutIssues() {
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Collections.emptyList());
        when(inferTypeVisitor.getType((Expression) resource.getContents()
            .get(0))).thenReturn(TypeEnum.ANY);
        @SuppressWarnings("unchecked")
        var listener = mock(((Class<Consumer<Collection<Issue>>>) ((Class<?>) Consumer.class)));
        subject.addValidationListener(listener);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        verify(listener).accept(issues);
    }

    @Test
    public void testValidationListenerWithIssues() {
        final var originalIssue = new SimpleIssue("TEST", true);
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Arrays.asList(originalIssue));
        when(inferTypeVisitor.getType((Expression) resource.getContents()
            .get(0))).thenReturn(TypeEnum.ANY);
        @SuppressWarnings("unchecked")
        var listener = mock(((Class<Consumer<Collection<Issue>>>) ((Class<?>) Consumer.class)));
        subject.addValidationListener(listener);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        verify(listener).accept(issues);
    }

}
