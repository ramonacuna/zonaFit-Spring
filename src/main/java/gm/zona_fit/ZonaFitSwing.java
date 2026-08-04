package gm.zona_fit;


import com.formdev.flatlaf.FlatDarculaLaf;
import gm.zona_fit.gui.ZonaFitForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class  ZonaFitSwing {
    public static void main(String[] args) {
        //Modo Oscuro
        FlatDarculaLaf.setup();
        //Instanciamos la fabrica de Spring
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ZonaFitSwing.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        //Creamos un objeto Swing
        SwingUtilities.invokeLater(() ->{

            ZonaFitForm zonaFit = applicationContext.getBean(ZonaFitForm.class);
            zonaFit.setVisible(true);
        });
    }
}
