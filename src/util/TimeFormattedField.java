package util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 * Classe para criar campos formatados com valores em decimal Tanto para valores
 * simples, como para exibir máscaras monetárias ou porcentagem
 *
 * @author Sekkuar
 *
 */
public class TimeFormattedField extends JFormattedTextField {

    /**
     * Constantes para definir o formato
     */
    public static final String HORA = "HH:mm";

    /**
     * Valor inserido no campo
     */
    private String dValue = "00:00";

    /**
     * Ultimo valor válido
     */
    private String oldValue = "00:00";

    /**
     * String que será exibida em caso de valor inválido ou fora do formato
     */
    public static final String ERRO = "Valor inválido";

    /**
     * Cria um novo objeto DecimalFormattedField, com o formato especificado
     *
     * @param formato Uma String que será o formato (pattern) do DecimalFormat a
     * ser utilizado
     */
    public TimeFormattedField(String formato) {
        super();
        this.setHorizontalAlignment(JTextField.RIGHT);
        setValue(oldValue);
        applyActions();
    }

    /**
     * Aplica um novo DecimalFormat
     */
    public void setDf(String df) {
        setValue("00:00");
        this.thisFocusGained(null);
        setText("00:00");
    }

    /**
     * Retorna o DecimalFormat sendo usado atualmente
     */
//    public String getDf() {  
//        return df;  
//    }  
    /**
     * Cria as ações de focus para setar e verificar os valores digitados
     */
    private void applyActions() {

        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                transferFocus();
            }
        });

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent evt) {
                thisFocusLost(evt);
            }

            @Override
            public void focusGained(FocusEvent evt) {
                thisFocusGained(evt);
            }
        });

    }

    /**
     * Verifica se o valor digitado é válido, e insere os novos valores
     */
    public void thisFocusLost(FocusEvent evt) {
        String valor = getText().trim();
        if (!valor.equals("") && !valor.trim().isEmpty()) {
            oldValue = valor;
        }
        setValue(valor);
    }

    /**
     * Limpa o campo para que seja digitado novo valor
     */
    public void thisFocusGained(FocusEvent fe) {
        super.setText("");
        normalText();
    }

    /**
     * Seta os valores no campo
     */
    private void setValue(String value) {
        try {
            dValue = value;
        } catch (Exception e) {
            /**
             * Aqui o valor é inválido, então coloca novamente o valor antigo
             */
            value = oldValue;
        }
        showValue(value);
    }

    @Override
    public void setValue(Object value) {
        this.setText(value.toString());
    }

    /**
     * Mostra o valor formatado no padrão do DecimalFormat
     *
     * @param s valor informado no campo
     */
    public void showValue(String s) {
        try {
            super.setText(s);
        } catch (Exception e) {
            
            /**
             * Valor inválido, exibe mensagem de erro
             */
            error();
            dValue = "";
        }
    }

    /**
     * Retorna o valor inserido no campo.
     *
     * @return dValue double value
     */
    public String getDoubleValue() {
        return dValue;
    }

    @Override
    public Object getValue() {
        return getDoubleValue();
    }

    /**
     * Mostra o texto ERRO em vermelho.
     */
    private void error() {
        this.setForeground(Color.red);
        super.setText(ERRO);
    }

    /**
     * Volta o texto ao formato original
     */
    private void normalText() {
        this.setForeground(null);
    }

    @Override
    public void setText(String valor) {
        super.setText(valor);
        this.thisFocusLost(null);
    }

}
