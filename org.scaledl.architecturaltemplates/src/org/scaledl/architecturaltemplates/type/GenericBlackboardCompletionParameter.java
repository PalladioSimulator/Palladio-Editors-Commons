/**
 */
package org.scaledl.architecturaltemplates.type;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Generic Blackboard Completion Parameter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.scaledl.architecturaltemplates.type.GenericBlackboardCompletionParameter#getFileExtension
 * <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getGenericBlackboardCompletionParameter()
 * @model
 * @generated
 */
public interface GenericBlackboardCompletionParameter extends CompletionParameter {
    /**
     * Returns the value of the '<em><b>File Extension</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Extension</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>File Extension</em>' attribute.
     * @see #setFileExtension(String)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getGenericBlackboardCompletionParameter_FileExtension()
     * @model required="true"
     * @generated
     */
    String getFileExtension();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.type.GenericBlackboardCompletionParameter#getFileExtension
     * <em>File Extension</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>File Extension</em>' attribute.
     * @see #getFileExtension()
     * @generated
     */
    void setFileExtension(String value);

} // GenericBlackboardCompletionParameter
