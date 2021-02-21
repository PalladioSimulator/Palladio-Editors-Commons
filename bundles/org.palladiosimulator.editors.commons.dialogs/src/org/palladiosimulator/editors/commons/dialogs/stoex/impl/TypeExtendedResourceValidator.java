package org.palladiosimulator.editors.commons.dialogs.stoex.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.service.OperationCanceledError;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.analyser.exceptions.ExpectedTypeMismatchIssue;
import de.uka.ipd.sdq.stoex.analyser.visitors.ExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeCheckVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class TypeExtendedResourceValidator implements IResourceValidator {

    private IResourceValidator originalValidator;
    private final Collection<Consumer<Collection<Issue>>> validationListeners = new ArrayList<>();
    private final ExpressionInferTypeVisitor typeVisitor;
    private final TypeEnum expectedType;

    public TypeExtendedResourceValidator(IResourceValidator originalValidator, ExpressionInferTypeVisitor typeVisitor, TypeEnum expectedType) {
        this.originalValidator = originalValidator;
        this.typeVisitor = typeVisitor;
        this.expectedType = expectedType;
    }

    @Override
    public List<Issue> validate(Resource resource, CheckMode mode, CancelIndicator indicator)
            throws OperationCanceledError {
        var issues = new ArrayList<>(originalValidator.validate(resource, mode, indicator));
        var additionalIssues = Optional.ofNullable(resource)
            .map(Resource::getContents)
            .filter(c -> !c.isEmpty())
            .map(c -> c.get(0))
            .filter(Expression.class::isInstance)
            .map(Expression.class::cast)
            .map(expr -> assertType(expr, typeVisitor, expectedType))
            .orElse(Collections.emptyList());
        issues.addAll(additionalIssues);
        notifyListeners(issues);
        return issues;
    }
    
    public void addValidationListener(Consumer<Collection<Issue>> listener) {
        validationListeners.add(listener);
    }
    
    protected void notifyListeners(Collection<Issue> issues) {
        validationListeners.forEach(listener -> listener.accept(issues));
    }

    protected Collection<Issue> assertType(final Expression result, final ExpressionInferTypeVisitor typeVisitor,
            final TypeEnum expectedType) {
        if (!TypeCheckVisitor.typesCompatible(expectedType, typeVisitor.getType(result))) {
            var issue = new ExpectedTypeMismatchIssue(expectedType, typeVisitor.getType(result));
            return Collections.singletonList(convert(issue));
        }
        return Collections.emptyList();
    }
    
    protected Issue convert(ExpectedTypeMismatchIssue issue) {
        return new Issue() {
            
            @Override
            public boolean isSyntaxError() {
                return false;
            }
            
            @Override
            public URI getUriToProblem() {
                return null;
            }
            
            @Override
            public CheckType getType() {
                return CheckType.FAST;
            }
            
            @Override
            public Severity getSeverity() {
                return Severity.ERROR;
            }
            
            @Override
            public Integer getOffset() {
                return 0;
            }
            
            @Override
            public String getMessage() {
                return issue.getMessage();
            }
            
            @Override
            public Integer getLineNumberEnd() {
                return 0;
            }
            
            @Override
            public Integer getLineNumber() {
                return 0;
            }
            
            @Override
            public Integer getLength() {
                return 0;
            }
            
            @Override
            public String[] getData() {
                return new String[0];
            }
            
            @Override
            public Integer getColumnEnd() {
                return 0;
            }
            
            @Override
            public Integer getColumn() {
                return 0;
            }
            
            @Override
            public String getCode() {
                return "TypeIncompatible";
            }
        };
    }

}
