/**
 *
 */
package org.palladiosimulator.editors.commons.dialogs.stoex;

import java.lang.reflect.InvocationTargetException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Lexer;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;

// TODO: Auto-generated Javadoc
/**
 * The Class ANTLRTokenScannerAdapter.
 *
 * @author Steffen Becker
 */
public class ANTLRTokenScannerAdapter implements ITokenScanner {

    /** The last token length. */
    int lastTokenLength = 0;

    /** The token offset. */
    private int tokenOffset;

    /** The scanner class. */
    private final Class<?> scannerClass;

    /** The scanner. */
    private Lexer scanner;

    /** The current input. */
    private String currentInput;

    /** The base offset. */
    private int baseOffset;

    /** The my mapper. */
    private final ITokenMapper myMapper;

    /**
     * Instantiates a new aNTLR token scanner adapter.
     *
     * @param scannerClass
     *            the scanner class
     * @param mapper
     *            the mapper
     */
    public ANTLRTokenScannerAdapter(final Class<?> scannerClass, final ITokenMapper mapper) {
        this.scannerClass = scannerClass;
        this.myMapper = mapper;
    }

    /**
     * Gets the token length.
     *
     * @return the token length
     * @see org.eclipse.jface.text.rules.ITokenScanner#getTokenLength()
     */
    @Override
    public int getTokenLength() {
        return lastTokenLength;
    }

    /**
     * Gets the token offset.
     *
     * @return the token offset
     * @see org.eclipse.jface.text.rules.ITokenScanner#getTokenOffset()
     */
    @Override
    public int getTokenOffset() {
        return tokenOffset;
    }

    /**
     * Next token.
     *
     * @return the i token
     * @see org.eclipse.jface.text.rules.ITokenScanner#nextToken()
     */
    @Override
    public IToken nextToken() {
        tokenOffset = baseOffset + scanner.getCharIndex(); // Token starts at point where lexer is
        final ANTLRTokenWrapper wrapper = new ANTLRTokenWrapper(scanner.nextToken(), myMapper);
        lastTokenLength = wrapper.getToken().getText() == null ? 0 : wrapper.getToken().getText().length();
        tokenOffset += (scanner.getCharIndex() + baseOffset) - tokenOffset - lastTokenLength; // Correct
        // the
        // position
        // in
        // case
        // of
        // recognition
        // exceptions

        wrapper.setIsWhitespace(wrapper.getToken().getChannel() == BaseRecognizer.HIDDEN); // Token
        // is a
        // Whitespace
        return wrapper;
    }

    /**
     * Sets the range.
     *
     * @param document the document
     * @param offset the offset
     * @param length the length
     * @see org.eclipse.jface.text.rules.ITokenScanner#setRange(org.eclipse.jface.text.IDocument,
     * int, int)
     */
    @Override
    public void setRange(final IDocument document, final int offset, final int length) {
        currentInput = "";
        try {
            currentInput = document.get(offset, length);
            tokenOffset = offset;
            baseOffset = offset;
        } catch (final BadLocationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        final ANTLRStringStream reader = new ANTLRStringStream(currentInput);
        try {
            scanner = (Lexer) scannerClass.getConstructor(CharStream.class).newInstance(new Object[] { reader });
        } catch (final IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
