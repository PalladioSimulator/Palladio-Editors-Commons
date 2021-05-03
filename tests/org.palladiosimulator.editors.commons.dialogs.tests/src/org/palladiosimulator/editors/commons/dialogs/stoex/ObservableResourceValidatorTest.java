package org.palladiosimulator.editors.commons.dialogs.stoex;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.editors.commons.dialogs.stoex.impl.ObservableResourceValidator;

import tools.mdsd.library.standalone.initialization.StandaloneInitializationException;
import tools.mdsd.library.standalone.initialization.StandaloneInitializerBuilder;

public class ObservableResourceValidatorTest {

    private ObservableResourceValidator subject;
    private IResourceValidator validatorMock;
    private Resource resource;

    @BeforeAll
    public static void init() throws StandaloneInitializationException {
        StandaloneInitializerBuilder.builder()
            .build()
            .init();
    }

    @BeforeEach
    public void setup() {
        validatorMock = mock(IResourceValidator.class);
        subject = new ObservableResourceValidator(validatorMock);
        resource = mock(Resource.class);
    }

    @Test
    public void testReturnViolations() {
        List<Issue> expectedIssues = new ArrayList<>();
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl)).thenReturn(expectedIssues);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        assertThat(issues, is(equalTo(expectedIssues)));
    }

    @Test
    public void testValidationListenerWithoutIssues() {
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Collections.emptyList());
        @SuppressWarnings("unchecked")
        var listener = mock(((Class<Consumer<Collection<Issue>>>) ((Class<?>) Consumer.class)));
        subject.addValidationListener(listener);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        verify(listener).accept(issues);
    }

    @Test
    public void testValidationListenerWithIssues() {
        final var originalIssue = mock(Issue.class);
        when(validatorMock.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl))
            .thenReturn(Arrays.asList(originalIssue));
        @SuppressWarnings("unchecked")
        var listener = mock(((Class<Consumer<Collection<Issue>>>) ((Class<?>) Consumer.class)));
        subject.addValidationListener(listener);
        var issues = subject.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        verify(listener).accept(issues);
    }

}
