package util;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class OnlyNumberFieldDocument extends PlainDocument {

    private int maxlength;

    public OnlyNumberFieldDocument() {
    }

    public OnlyNumberFieldDocument(int maxlength) {
        super();
        this.maxlength = maxlength;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            str = "";
        }
        try {
            boolean fixedLengh = (!((getLength() + str.length()) > maxlength));
            if (maxlength == 0 || fixedLengh) {
                super.insertString(offs, str, a);
            }
        } catch (BadLocationException e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }
}