package gm.zona_fit.servicio;

import gm.zona_fit.Repositorio.RepositorioCliente;
import gm.zona_fit.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Override
    public List<Cliente> listarClientes() {
        return repositorioCliente.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        return repositorioCliente.findById(idCliente).orElse(null);
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        repositorioCliente.save(cliente);
    }

    @Override
    public void borrarCliente(Integer idCliente) {
        repositorioCliente.deleteById(idCliente);
    }
}
