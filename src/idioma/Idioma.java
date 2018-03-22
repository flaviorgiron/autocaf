package idioma;

import java.util.Properties;
import util.GeraLog;

public class Idioma extends Properties {

    private static final long serialVersionUID = 1L;

    public Idioma(String idioma) {

        //Modificar si quieres añadir mas idiomas
        //Cambia el nombre de los ficheros o añade los necesarios
        switch (idioma) {
            case "espanhol":
                getProperties("espanhol.properties");
                break;
            case "portugues":
                getProperties("portugues.properties");
                break;
            default:
                getProperties("portugues.properties");
        }

    }

    private void getProperties(String idioma) {
        try {
            this.load(getClass().getResourceAsStream(idioma));
        } catch (Exception e) {
            GeraLog g = new GeraLog();
            g.gravaErro(e);
            g.close();
            e.printStackTrace();
        }
    }
}
