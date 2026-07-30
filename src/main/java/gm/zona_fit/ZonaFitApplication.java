package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando ZonaFitApplication");
		//Inicio la fabrica de Spring
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Terminando ZonaFitApplication");
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		var exitApp = false;
		while (!exitApp) {
			var option = mostrarMenu(sc);
			switch (option) {
				case 1 -> clienteServicio.listarClientes().forEach(System.out::println);
				case 2 -> buscarCliente(sc);
				case 3 -> agregarCliente(sc);
				case 4 -> agregarCliente(sc);
				case 5 -> eliminarCliente(sc);
				case 6 -> {
					logger.info("Hasta Pronto!...");
					exitApp = true;
				}
				default -> System.out.println("Opcion no reconocida: " + option);
			}

		}

	}


	private static int leerEntero(Scanner sc, String mensaje) {
		while (true) {
			logger.info(mensaje);
			try {
				return Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				logger.info("Error: Por favor, ingrese un número entero válido.\n");
			}
		}
	}

	private static int mostrarMenu(Scanner sc) {
		var menu = """
                *** Zona Fit (GYM) ***
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elija una Opcion:\s""";
		return leerEntero(sc, menu);
	}


	private void buscarCliente(Scanner sc) {
		Integer idCliente = leerEntero(sc,"Ingresa el ID del cliente: ");
		logger.info(clienteServicio.buscarClientePorId(idCliente).toString());
	}

	private void agregarCliente(Scanner sc) {
		Integer idCliente = leerEntero(sc,"Ingresa el ID del cliente: ");
		logger.info("Ingresa el nombre del cliente: ");
		String nombre = sc.nextLine();
		logger.info("Ingresa el apellido del cliente: ");
		String apellido = sc.nextLine();
		logger.info("Ingresa el email del cliente: ");
		Integer membresia = leerEntero(sc,"Ingresa el membresia: ");

		clienteServicio.guardarCliente(new Cliente(idCliente, nombre, apellido, membresia));
	}

	private void eliminarCliente(Scanner sc) {
		Integer idCliente = leerEntero(sc,"Ingresa el ID del cliente: ");
		clienteServicio.borrarCliente(idCliente);
	}



}
