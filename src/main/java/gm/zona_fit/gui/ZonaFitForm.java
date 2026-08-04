package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class ZonaFitForm extends JFrame {
    private final ClienteServicio clienteServicio;
    private JPanel mainPanel;
    private JTable clientesTabla;
    private JPanel tablaPanel;
    private JPanel viewPanel;
    private JPanel buttomPanel;
    private JTextField apellidoText;
    private JTextField membresiaText;
    private JTextField nombreText;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private DefaultTableModel tablaModeloClientes;
    private Integer idCargado;

    @Autowired
    public ZonaFitForm(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio ;
        iniciarForma();
        guardarButton.addActionListener(e -> {
            guardarCliente();
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTexto();
            }
        });
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idCargado != null){
                    try {
                        clienteServicio.borrarCliente(idCargado);
                        limpiarTexto();
                        listarClientes();
                        mostrarMensaje("Cliente eliminado exitosamente");
                    } catch (Exception ex) {
                        mostrarMensaje("Error al eliminar el cliente: " + ex.getMessage());
                    }
                } else {
                    mostrarMensaje("Selecciona un cliente de la tabla para eliminar");
                }
            }
        });
    }

    private void cargarClienteSeleccionado() {
        var renglon = clientesTabla.getSelectedRow();
        if (renglon != -1) {
            try {
                var id = clientesTabla.getModel().getValueAt(renglon, 0).toString();
                var nombre = clientesTabla.getModel().getValueAt(renglon, 1).toString();
                var apellido = clientesTabla.getModel().getValueAt(renglon, 2).toString();
                var membresia = clientesTabla.getModel().getValueAt(renglon, 3).toString();

                this.idCargado = Integer.parseInt(id);
                nombreText.setText(nombre);
                apellidoText.setText(apellido);
                membresiaText.setText(membresia);
            } catch (Exception ex) {
                mostrarMensaje("Error al cargar la información del cliente seleccionado");
            }
        }
    }

    private void limpiarTexto() {
        this.idCargado = null;
        nombreText.setText("");
        apellidoText.setText("");
        membresiaText.setText("");
    }

    private void guardarCliente() {
       Cliente cliente = new  Cliente();
        if (this.idCargado != null) {
            cliente.setId(this.idCargado); // Requerido para actualizar en JPA
        }
       if(nombreText.getText().isEmpty()){
           mostrarMensaje("Proporciona Nombre");
           nombreText.requestFocus();
           return;
       }else if(apellidoText.getText().isEmpty()){
           mostrarMensaje("Proporciona Apellido");
           apellidoText.requestFocus();
           return;
       }else if(membresiaText.getText().isEmpty()){
           mostrarMensaje("Proporciona Membresia");
           membresiaText.requestFocus();
           return;
       }else if(!nombreText.getText().isEmpty() || !apellidoText.getText().isEmpty() || !membresiaText.getText().isEmpty()){
           try{
               if(idCargado == null){
                   this.clienteServicio.membresiaExistente(Integer.parseInt(membresiaText.getText()));
               }
               cliente.setNombre(nombreText.getText());
               cliente.setApellido(apellidoText.getText());
               cliente.setMembresia(Integer.parseInt(membresiaText.getText()));
               clienteServicio.guardarCliente(cliente);
               //Borramos la entradas de texto y refrescamos la tablaModeloCliente
               limpiarTexto();
               listarClientes();

           }catch(NumberFormatException e) {
               mostrarMensaje("La membresía debe ser un número entero válido");
               membresiaText.requestFocus();
           } catch (IllegalArgumentException e) {
               // Captura el error lanzado por membresiaExistente
               mostrarMensaje(e.getMessage());
               membresiaText.requestFocus();
           }
       }

    }


    private void mostrarMensaje(String titulo){
        JOptionPane.showMessageDialog(this, titulo);
    }

    private void iniciarForma() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //this.tablaModeloClientes = new DefaultTableModel(0,4);
        this.tablaModeloClientes = new DefaultTableModel(0,4){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        String[] cabeceros = {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloClientes.setColumnIdentifiers(cabeceros);
        this.clientesTabla = new JTable(tablaModeloClientes);
        listarClientes();
    }

    private void listarClientes() {
        this.tablaModeloClientes.setRowCount(0);
        try{
            List<Cliente> clientes = this.clienteServicio.listarClientes();
            clientes.forEach(cliente -> {
                Object[] valuesClientes = {
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getMembresia()
                };
                this.tablaModeloClientes.addRow(valuesClientes);
            });
        }catch(Exception e){
            mostrarMensaje("Error al cargar la lista de clientes desde la base de datos");
        }
    }
}
