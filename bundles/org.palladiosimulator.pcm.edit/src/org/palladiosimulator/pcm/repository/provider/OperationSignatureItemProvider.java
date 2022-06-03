package org.palladiosimulator.pcm.repository.provider;

import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;

public class OperationSignatureItemProvider extends OperationSignatureItemProviderGen{


    /**
     * {@inheritDoc}
     */
    public OperationSignatureItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object)
	{
		var signature = ((OperationSignature)object);

        var interfaceName = "<<Interface>>";
        if (signature.getInterface__OperationSignature() != null) {
            interfaceName = signature.getInterface__OperationSignature().getEntityName();
        }
        var signatureName = signature.getEntityName();

        var parameters = signature.getParameters__OperationSignature().stream().map(Parameter::getParameterName)
                .collect(Collectors.joining(","));

        return String.format("%s::%s(%s)", interfaceName, signatureName, parameters);
	}

}
