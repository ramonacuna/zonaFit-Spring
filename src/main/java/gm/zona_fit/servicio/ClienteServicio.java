package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Cliente;

import java.util.List;

public interface ClienteServicio {
     List<Cliente> listarClientes();
     Cliente buscarClientePorId(Integer idCliente);
     void guardarCliente(Cliente cliente);
     void borrarCliente(Integer idCliente);
     void membresiaExistente(Integer membresia);

}
