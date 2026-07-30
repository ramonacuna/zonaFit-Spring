package gm.zona_fit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Utils {
    public static int leerEntero(Scanner sc, Logger logger, String mensaje) {
        while (true) {
            logger.info(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                logger.info("Error: Por favor, ingrese un número entero válido.\n");
            }
        }
    }}
