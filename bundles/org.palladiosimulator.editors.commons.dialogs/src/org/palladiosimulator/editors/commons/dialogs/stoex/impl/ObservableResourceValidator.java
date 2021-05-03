package org.palladiosimulator.editors.commons.dialogs.stoex.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

public class ObservableResourceValidator implements IResourceValidator {

    private IResourceValidator originalValidator;
    private final Collection<Consumer<Collection<Issue>>> validationListeners = new ArrayList<>();

    public ObservableResourceValidator(IResourceValidator originalValidator) {
        this.originalValidator = originalValidator;
    }

    @Override
    public List<Issue> validate(Resource resource, CheckMode mode, CancelIndicator indicator)
            throws OperationCanceledError {
        var issues = originalValidator.validate(resource, mode, indicator);
        notifyListeners(issues);
        return issues;
    }

    public void addValidationListener(Consumer<Collection<Issue>> listener) {
        validationListeners.add(listener);
    }

    protected void notifyListeners(Collection<Issue> issues) {
        validationListeners.forEach(listener -> listener.accept(issues));
    }

}
