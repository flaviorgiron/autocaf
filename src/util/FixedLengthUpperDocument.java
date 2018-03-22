package util;

import javax.swing.text.*;

public class FixedLengthUpperDocument extends PlainDocument {

    private int iMaxLength;

    public FixedLengthUpperDocument(int maxlen) {
        super();
        iMaxLength = maxlen;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        if (iMaxLength <= 0) {// aceitara qualquer no. de caracteres
            super.insertString(offset, str.toUpperCase(), attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength) { // se o comprimento final for menor...
            super.insertString(offset, str.toUpperCase(), attr);
        } else {
            if (getLength() == iMaxLength) {
                return; // nada a fazer
            }
            String newStr = str.substring(0, (iMaxLength - getLength()));

            super.insertString(offset, newStr.toUpperCase(), attr);
        }
    }
}
