package view.movimentacao;

public class TerminalMovelThread implements Runnable {

    @Override
    public void run() {
        try {
            TerminalMovel1.main(null);
        } catch (Exception e) {
        }
    }

}
