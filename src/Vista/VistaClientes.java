/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;
import Controlador.ClienteControlador;
import Modelo.Cliente;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.awt.FileDialog;
import javax.swing.JOptionPane;
/**
 *
 * @author YALESKA
 */
public class VistaClientes extends javax.swing.JPanel {
     private final ClienteControlador clienteControlador;
    private Integer idClienteSeleccionado = null;
    /**
     * Creates new form VistaCliente
     */
    public VistaClientes() {
        initComponents();
        this.clienteControlador = new ClienteControlador();
        cargarDatosTabla();
    }
    
     
    public void cargarDatosTabla() {
        //Obtener todas las categorias del controlador
        List<Cliente> clientes = clienteControlador.obtenerTodosClientes();
        if (clientes != null) {
            // obtener el modelo existente de la tabla
            DefaultTableModel model = (DefaultTableModel) tablaCliente.getModel();
            model.setRowCount(0);
            //llenar las filas con los datos de categorias  
            for (Cliente cli : clientes) {
                Object[] row = {
                    cli.getIdCliente(),
                    cli.getPrimerNombre(),
                    cli.getSegundoNombre(),
                    cli.getPrimerApellido(),
                    cli.getSegundoApellido(),
                    cli.getCelular(),
                    cli.getDireccion(),
                    cli.getCedula() 
                };
                model.addRow(row);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlabel1 = new javax.swing.JLabel();
        textprimer_nombre = new javax.swing.JTextField();
        jlabel2 = new javax.swing.JLabel();
        textsegundo_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textprimer_apellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textsegundo_apellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textcelular = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textdireccion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        Buscar = new javax.swing.JButton();
        textBuscarCliente = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        textcedula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        btnGenerarReporte = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));

        jlabel1.setText(" Primer_nombre");

        textprimer_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textprimer_nombreActionPerformed(evt);
            }
        });

        jlabel2.setText("Segundo_nombre");

        textsegundo_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsegundo_nombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Primer_apellido");

        textprimer_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textprimer_apellidoActionPerformed(evt);
            }
        });

        jLabel4.setText("Segundo_apellido");

        textsegundo_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsegundo_apellidoActionPerformed(evt);
            }
        });

        jLabel5.setText("Celular");

        textcelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textcelularActionPerformed(evt);
            }
        });

        jLabel6.setText("Direccion");

        textdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textdireccionActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnbtnGuardar(evt);
            }
        });

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        textBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarClienteKeyTyped(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnbtnEliminar(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnActualizar(evt);
            }
        });

        textcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textcedulaActionPerformed(evt);
            }
        });

        jLabel7.setText("Cedula");

        tablaCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idCliente", "primer_nombre", "segundo_nombre", "primer_apellido", "Segundo_apellido", "celular", "direccion", "cedula"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCliente);

        btnGenerarReporte.setText("Generar Reportes");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBotonGenerarReporte(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textprimer_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textsegundo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textprimer_apellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textsegundo_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGenerarReporte))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textprimer_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textsegundo_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textsegundo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textprimer_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGenerarReporte)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textprimer_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textprimer_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textprimer_nombreActionPerformed

    private void textsegundo_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsegundo_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textsegundo_nombreActionPerformed

    private void textprimer_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textprimer_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textprimer_apellidoActionPerformed

    private void textsegundo_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsegundo_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textsegundo_apellidoActionPerformed

    private void textcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcelularActionPerformed

    private void textdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textdireccionActionPerformed

    private void accionbtnbtnGuardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnbtnGuardar
        String primer_nombre = textprimer_nombre.getText();
        String segundo_nombre = textsegundo_nombre.getText();
        String primer_apellido = textprimer_apellido.getText();
        String segundo_apellido = textsegundo_apellido.getText();
        String celular =textcelular.getText();
        String direccion = textdireccion.getText();
        String cedula = textcedula.getText();
       
        if (!primer_nombre.isEmpty() && !segundo_nombre.isEmpty() && !primer_apellido.isEmpty()&& !segundo_apellido.isEmpty()
            && ! celular .isEmpty()&& ! direccion .isEmpty()&& ! cedula .isEmpty()) {
            clienteControlador.crearCliente(primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,celular,direccion,cedula);
            cargarDatosTabla();
            textprimer_nombre.setText("");
            textsegundo_nombre.setText("");
            textprimer_apellido.setText("");
            textsegundo_apellido.setText("");
            textcelular.setText("");
            textdireccion.setText("");
            textcedula.setText("");
        } else {            
            javax.swing.JOptionPane.showMessageDialog(this, "por favor, llene todos los campos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionbtnbtnGuardar

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:            
    }//GEN-LAST:event_BuscarActionPerformed

    private void accionbtnbtnEliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnbtnEliminar
        int filaSelecionada = tablaCliente.getSelectedRow();
        if (filaSelecionada != -1) {
            int idCliente = (int) tablaCliente.getValueAt(filaSelecionada, 0);
            clienteControlador.eliminarCliente(idCliente);
            cargarDatosTabla();
                    } else {

            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionbtnbtnEliminar

    private void accionbtnActualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnActualizar
        String primer_nombre = textprimer_nombre.getText();
        String segundo_nombre = textsegundo_nombre.getText();
        String primer_apellido = textprimer_apellido.getText();
        String segundo_apellido = textsegundo_apellido.getText();
        String celular =textcelular.getText();
        String direccion = textdireccion.getText();
        String cedula = textcedula.getText();
        if (idClienteSeleccionado != null && !primer_nombre.isEmpty() && !segundo_nombre.isEmpty() && !primer_apellido.isEmpty()&& !segundo_apellido.isEmpty()
            && ! celular .isEmpty()&& ! direccion .isEmpty()&& ! cedula .isEmpty()) {
            clienteControlador.actualizarCliente(idClienteSeleccionado, primer_nombre,segundo_nombre,primer_apellido,segundo_apellido,celular,direccion,cedula);
            cargarDatosTabla();
            
           textprimer_nombre.setText("");
            textsegundo_nombre.setText("");
            textprimer_apellido.setText("");
            textsegundo_apellido.setText("");
            textcelular.setText("");
            textdireccion.setText("");
            textcedula.setText("");
            idClienteSeleccionado = null;
            
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "por favor, llene todos los campos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_accionbtnActualizar

    private void textcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcedulaActionPerformed

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
        if (evt.getClickCount() == 2) {
            
            int filaSelecionada = tablaCliente.getSelectedRow();
            
            if (filaSelecionada != -1) {
                idClienteSeleccionado = (int) tablaCliente.getValueAt(filaSelecionada, 0);
                String primer_nombre = (String) tablaCliente.getValueAt(filaSelecionada, 1);
                String segundo_nombre = (String) tablaCliente.getValueAt(filaSelecionada, 2);
                String primer_apellido = (String) tablaCliente.getValueAt(filaSelecionada, 3);
                String segundo_apellido = (String) tablaCliente.getValueAt(filaSelecionada, 4);
                String celular = (String)tablaCliente.getValueAt(filaSelecionada, 5);
                String direccion = (String)tablaCliente.getValueAt(filaSelecionada, 6);
                String cedula = (String)tablaCliente.getValueAt(filaSelecionada, 7);

                
                textprimer_nombre.setText(primer_nombre);
                textsegundo_nombre.setText(segundo_nombre);
                textprimer_apellido.setText(primer_apellido);
                textsegundo_apellido.setText(segundo_apellido);
                textcelular.setText(celular);
                textdireccion.setText(direccion);
                textcedula.setText(cedula);
                
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_tablaClienteMouseClicked

    private void buscarClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarClienteKeyTyped
        String textoBusqueda = textBuscarCliente.getText().trim().toLowerCase();
        List<Cliente> clientes = clienteControlador.obtenerTodosClientes();
        
        DefaultTableModel modelo = (DefaultTableModel) tablaCliente.getModel();
        modelo.setRowCount(0);
        
        if (clientes != null) {
            for (Cliente cli : clientes) {
                if (textoBusqueda.isEmpty()|| 
                   cli.getPrimerNombre().toLowerCase().contains(textoBusqueda)||
                   cli.getSegundoNombre().toLowerCase().contains(textoBusqueda)||
                   cli.getPrimerApellido().toLowerCase().contains(textoBusqueda)||
                   cli.getSegundoApellido().toLowerCase().contains(textoBusqueda)||
                   cli.getCelular().toLowerCase().contains(textoBusqueda)||
                   cli.getDireccion().toLowerCase().contains(textoBusqueda)||
                   cli.getCedula().toLowerCase().contains(textoBusqueda)){  
                         Object[] fila = {
                        cli.getIdCliente(),
                        cli.getPrimerNombre(),
                        cli.getSegundoNombre(),
                        cli.getPrimerApellido(),
                        cli.getSegundoApellido(),
                        cli.getCelular(),
                        cli.getDireccion(),
                        cli.getCedula()
                    };
                    modelo.addRow(fila);
                }
            }
        }        
    }//GEN-LAST:event_buscarClienteKeyTyped

    private void accionBotonGenerarReporte(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonGenerarReporte
        // TODO add your handling code here:
         try {
            FileDialog dialogoArchivo = new FileDialog((java.awt.Frame) null, "Guardar Reporte PDF",FileDialog.SAVE);
            dialogoArchivo.setFile("ReportesClientes.pdf");
            dialogoArchivo.setVisible(true);
            
        String ruta = dialogoArchivo.getDirectory();
        String nombreArchivo = dialogoArchivo.getFile();
        
        if (ruta == null ||nombreArchivo == null){
            JOptionPane.showMessageDialog(this, "operacion cancelada", "informacion",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String rutaCompleta = ruta + nombreArchivo;
        
        PdfWriter escritor = new PdfWriter(rutaCompleta);
        PdfDocument pdf= new PdfDocument(escritor);
        Document documento = new Document(pdf);
        
        documento.add(new Paragraph("Reportes de Clientes")
        .setTextAlignment(TextAlignment.CENTER)
        .setFontSize(20)
        .setBold());
        
        documento.add(new Paragraph("Fecha:" + new java.util.Date().toString())
        .setTextAlignment(TextAlignment.CENTER)
        .setFontSize(12));
        
        Table tabla = new Table(8);
        tabla.setWidth(UnitValue.createPercentValue(100));
        tabla.addHeaderCell("ID Cliente").setBold();
        tabla.addHeaderCell("primer_nombre").setBold();
        tabla.addHeaderCell("segundo_nombre").setBold();
        tabla.addHeaderCell("primer_apellido").setBold();
        tabla.addHeaderCell("segundo_apellido").setBold();
        tabla.addHeaderCell("celular").setBold();
        tabla.addHeaderCell("direccion").setBold();
        tabla.addHeaderCell("cedula").setBold();
        
        List<Cliente> listaClientes =
        clienteControlador.obtenerTodosClientes();
        if (listaClientes != null){
            for (Cliente cliente : listaClientes){
                tabla.addCell(String.valueOf(cliente.getIdCliente()));
                tabla.addCell(String.valueOf(cliente.getPrimerNombre()));
                tabla.addCell(String.valueOf(cliente.getSegundoNombre()));
                tabla.addCell(String.valueOf(cliente.getPrimerApellido()));
                tabla.addCell(String.valueOf(cliente.getSegundoApellido()));
                tabla.addCell(String.valueOf(cliente.getCelular()));
                tabla.addCell(String.valueOf(cliente.getDireccion()));
                tabla.addCell(String.valueOf(cliente.getCedula()));
            }
        }
        documento.add(tabla);
        
        documento.add(new Paragraph("Notas: Reportes generado automaticamente desde el sistema.")
        .setFontSize(10)
        .setMarginTop(20));
        
        documento.close();
        
        JOptionPane.showMessageDialog(
                    this,
                "Reporte pdf generado con exito en:" + rutaCompleta,
                "Exito", JOptionPane.INFORMATION_MESSAGE);
        
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Error al Generar el PDF:" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE );
        }

    }//GEN-LAST:event_accionBotonGenerarReporte


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel jlabel2;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTextField textBuscarCliente;
    private javax.swing.JTextField textcedula;
    private javax.swing.JTextField textcelular;
    private javax.swing.JTextField textdireccion;
    private javax.swing.JTextField textprimer_apellido;
    private javax.swing.JTextField textprimer_nombre;
    private javax.swing.JTextField textsegundo_apellido;
    private javax.swing.JTextField textsegundo_nombre;
    // End of variables declaration//GEN-END:variables
}
