/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.VentaControlador;
import Modelo.Venta;
import Controlador.DetalleVentaControlador;
import Modelo.DetalleVenta;
import Controlador.ClienteControlador;
import Modelo.Cliente;
import Controlador.EmpleadoControlador;
import Modelo.Empleado;
import Controlador.ProductoControlador;
import Modelo.Producto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
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
public class VistaVentas extends javax.swing.JPanel {

    private final VentaControlador ventaControlador;
    private final DetalleVentaControlador detalleVentaControlador;
    private final EmpleadoControlador empleadoControlador;
    private final ClienteControlador clienteControlador;
    private final ProductoControlador productoControlador;
    private Integer idEmpleadoSeleccionado = null;
    private Integer idClienteSeleccionado = null;
    private Integer idProductoSeleccionado = null;
    private Timer timer; // Variable de instancia para el Timer
    private boolean horabd = false;

    /**
     * Creates new form VistaVenta
     */
    public VistaVentas() {
        initComponents();
        this.ventaControlador = new VentaControlador();
        this.detalleVentaControlador = new DetalleVentaControlador();
        this.empleadoControlador = new EmpleadoControlador();
        this.clienteControlador = new ClienteControlador();
        this.productoControlador = new ProductoControlador();
        eventoComboProductos();
        selectorfechaVenta.setDate(new Date());
        ((JTextField) selectorfechaVenta.getDateEditor().getUiComponent()).setEditable(false);

        // Limpiar las filas vacías de tablaDetalles
        DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
        modelDetalles.setRowCount(0);

        cargarDatosTablaVentas();
        cargarClientes();
        cargarEmpleados();
        cargarProductos();
        actualizarHora();
    }

    private void limpiar() {
        textBuscar.setText("");
        idEmpleadoSeleccionado = null;
        selectorfechaVenta.setDate(new Date());

        // Limpiar la tabla de detalles
        tablaDetalles.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID Producto", "Producto", "Precio Unitario", "Cantidad", "Subtotal"}));

        cargarDatosTablaVentas();
        cargarClientes();
        cargarEmpleados();
        cargarProductos();

        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(true);

        horabd = false; // Restablece para mostrar hora actual
        actualizarHora(); // Vuelve a iniciar el timer
    }

    private void actualizarHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Managua"));

        // Detener el timer anterior si existe
        if (timer != null) {
            timer.stop();
        }

        if (horabd) {
            return; // No actualiza la hora si está cargada desde la base de datos
        }

        timer = new Timer(1000, e -> {
            Date now = new Date();
            hora.setText(sdf.format(now));
        });
        timer.start();
    }

    private void cargarDatosTablaVentas() {
        List<Venta> ventas = ventaControlador.obtenerTodasVentas();

        if (ventas != null) {
            DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();
            model.setRowCount(0);

            for (Venta v : ventas) {
                Cliente cliente = clienteControlador.obtenerClientePorId(v.getIdCliente());
                String nombreCliente = cliente.getPrimerNombre() + " " + cliente.getPrimerApellido();

                Empleado empleado = empleadoControlador.obtenerEmpleadoPorId(v.getIdEmpleado());
                String nombreEmpleado = empleado.getPrimerNombre() + " " + empleado.getPrimerApellido();

                Object[] row = {
                    v.getIdVenta(),
                    v.getFechaVenta(),
                    nombreCliente,
                    nombreEmpleado,
                    v.getTotalVenta()
                };
                model.addRow(row);
            }
        }
    }

    private void cargarClientes() {
        try {
            // Obtener las categorías desde el controlador
            List<Cliente> clientes = clienteControlador.obtenerTodosClientes();

            // Limpiar el combo box por si tiene datos
            comboClientes.removeAllItems();

            // Agregar cada categoría al combo box
            for (Cliente c : clientes) {
                comboClientes.addItem(c.getPrimerNombre() + " " + c.getPrimerApellido()); // Mostrar el nombre
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los clientes: " + e.getMessage());
        }
    }

    private void cargarEmpleados() {
        try {
            // Obtener las categorías desde el controlador
            List<Empleado> empleados = empleadoControlador.obtenerTodosEmpleados();

            // Limpiar el combo box por si tiene datos
            comboEmpleados.removeAllItems();

            // Agregar cada categoría al combo box
            for (Empleado e : empleados) {
                comboEmpleados.addItem(e.getPrimerNombre() + " " + e.getPrimerApellido()); // Mostrar el nombre
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los empleados: " + e.getMessage());
        }
    }

    private void cargarProductos() {
        try {
            // Obtener las categorías desde el controlador
            List<Producto> productos = productoControlador.obtenerTodosProductos();

            // Limpiar el combo box por si tiene datos
            comboProductos.removeAllItems();

            // Agregar cada categoría al combo box
            for (Producto p : productos) {
                comboProductos.addItem(p.getNombreProducto()); // Mostrar el nombre
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar los productos: " + e.getMessage());
        }
    }

    private void eventoComboProductos() {
        comboProductos.addActionListener(e -> {
            // Obtener el índice seleccionado
            int indiceSeleccionado = comboProductos.getSelectedIndex();

            if (indiceSeleccionado >= 0) { // Verificar que se haya seleccionado algo
                try {
                    // Obtener la lista de categorías desde el controlador o memoria
                    List<Producto> productos = productoControlador.obtenerTodosProductos();

                    // Obtener el objeto de categoría correspondiente al índice seleccionado
                    Producto productoSeleccionado = productos.get(indiceSeleccionado);

                    // Actualizar la variable global con el ID de la categoría seleccionada
                    idProductoSeleccionado = productoSeleccionado.getIdProducto();

                    // Actualizar el campo textPrecio con el precio unitario del producto
                    textPrecio.setText(String.valueOf(productoSeleccionado.getPrecioUnitario()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al seleccionar categoría: " + ex.getMessage());
                }
            }
        });

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
        jlabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textCantidad = new javax.swing.JTextField();
        textBuscar = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        comboClientes = new javax.swing.JComboBox<>();
        comboEmpleados = new javax.swing.JComboBox<>();
        selectorfechaVenta = new com.toedter.calendar.JDateChooser();
        hora = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboProductos = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnQuitarDetalle = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textPrecio = new javax.swing.JTextField();
        btnGenerarReporte = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jlabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jlabel1.setText("Cliente");

        jlabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jlabel2.setText("Empleado");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText(" fecha");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setText("Cantidad");

        textCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCantidadActionPerformed(evt);
            }
        });

        textBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBuscarActionPerformed(evt);
            }
        });
        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textBuscarKeyTyped(evt);
            }
        });

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        btnEliminar.setText("EliminarVenta");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnEliminar(evt);
            }
        });

        btnActualizar.setText("ActualizarVenta");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnActualizar(evt);
            }
        });

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Venta", "Fecha/Hora", "Cliente", "Vendedor", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablasVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnAgregar(evt);
            }
        });

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente 1", "Cliente 2", "Cliente 3", "Cliente 4" }));

        comboEmpleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado 1", "Empleado 2", "Empleado 3", "Empleado 4" }));

        hora.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        hora.setText("00:00:00");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("Producto");

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Producto 1", "Producto 2", "Producto 3", "Producto 4" }));

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_Producto", "Producto", "Precio Unitario", "Cantidad", "SubTotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaDetalles);

        btnGuardar.setText("GuardarVenta");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionBotonGuardar(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnLimpiar(evt);
            }
        });

        btnQuitarDetalle.setText("QuitarDetalles");
        btnQuitarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accionbtnQuitarDetalles(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setText("Precio");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerarReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitarDetalle)
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel3)
                                        .addGap(48, 48, 48)
                                        .addComponent(hora)
                                        .addGap(15, 15, 15))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(selectorfechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel2))
                                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel5)
                                        .addGap(35, 35, 35))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(selectorfechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarReporte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCantidadActionPerformed

    private void textBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBuscarActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarActionPerformed

    private void accionbtnEliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnEliminar
        try {
            // Obtener el índice de la fila seleccionada en tablaVentas
            int filaSeleccionada = tablaVentas.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una venta para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el idVenta de la fila seleccionada
            DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();
            int idVenta = (int) model.getValueAt(filaSeleccionada, 0);

            // Confirmar con el usuario antes de eliminar
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de que desea eliminar la venta con ID " + idVenta + "?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Eliminar la venta
                ventaControlador.eliminarVenta(idVenta);

                // Recargar la tabla de ventas
                cargarDatosTablaVentas();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionbtnEliminar

    private void accionbtnActualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnActualizar
        // TODO add your handling code here:
        try {
            // Obtener el índice de la fila seleccionada en tablaVentas
            int filaSeleccionada = tablaVentas.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una venta para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el idVenta de la fila seleccionada
            DefaultTableModel modelVentas = (DefaultTableModel) tablaVentas.getModel();
            int idVenta = (int) modelVentas.getValueAt(filaSeleccionada, 0);

            // Obtener el índice seleccionado de clientes y empleados
            int indiceCliente = comboClientes.getSelectedIndex();
            int indiceEmpleado = comboEmpleados.getSelectedIndex();
            if (indiceCliente < 0 || indiceEmpleado < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente y un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la lista de clientes y empleados
            List<Cliente> clientes = clienteControlador.obtenerTodosClientes();
            List<Empleado> empleados = empleadoControlador.obtenerTodosEmpleados();
            int idCliente = clientes.get(indiceCliente).getIdCliente();
            int idEmpleado = empleados.get(indiceEmpleado).getIdEmpleado();

            // Obtener la fecha seleccionada
            Date fechaVenta = selectorfechaVenta.getDate();
            if (fechaVenta == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener los detalles de la tabla tablaDetalles
            DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
            int rowCount = modelDetalles.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Agregue al menos un producto a la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular el total de la venta
            float totalVenta = 0;
            for (int i = 0; i < rowCount; i++) {
                totalVenta += ((Number) modelDetalles.getValueAt(i, 4)).floatValue(); // Suma los subtotales
            }

            // Actualizar la venta principal
            ventaControlador.actualizarVenta(idVenta, idCliente, idEmpleado, fechaVenta, totalVenta);

            // Eliminar los detalles antiguos de la venta
            List<DetalleVenta> detallesAntiguos = detalleVentaControlador.obtenerTodosDetallesVenta();
            if (detallesAntiguos != null) {
                for (DetalleVenta detalle : detallesAntiguos) {
                    if (detalle.getIdVenta() == idVenta) {
                        detalleVentaControlador.eliminarDetalleVenta(detalle.getIdDetalleVenta());
                    }
                }
            }

            // Insertar los nuevos detalles
            List<DetalleVenta> nuevosDetalles = new ArrayList<>();
            for (int i = 0; i < rowCount; i++) {
                int idProducto = (int) modelDetalles.getValueAt(i, 0);
                float precioUnitario = ((Number) modelDetalles.getValueAt(i, 2)).floatValue();
                int cantidad = (int) modelDetalles.getValueAt(i, 3);

                // Crear y guardar el nuevo detalle
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdVenta(idVenta);
                detalle.setIdProducto(idProducto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(precioUnitario);
                nuevosDetalles.add(detalle);
                detalleVentaControlador.crearDetalleVenta(idVenta, idProducto, cantidad, precioUnitario);
            }

            // Limpiar la tabla de detalles y el formulario
            tablaDetalles.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID Producto", "Producto", "Precio Unitario", "Cantidad", "Subtotal"}));
            limpiar();

            // Recargar la tabla de ventas
            cargarDatosTablaVentas();

            // Habilitar botones nuevamente
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionbtnActualizar

    private void accionbtnAgregar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnAgregar
        
        try {
            // Obtener el índice seleccionado del comboProductos
            int indiceSeleccionado = comboProductos.getSelectedIndex();
            if (indiceSeleccionado < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la lista de productos
            List<Producto> productos = productoControlador.obtenerTodosProductos();
            Producto productoSeleccionado = productos.get(indiceSeleccionado);

            // Obtener el precio unitario del producto
            float precioUnitario = productoSeleccionado.getPrecioUnitario();

            // Obtener la cantidad ingresada
            String cantidadStr = textCantidad.getText().trim();
            if (cantidadStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calcular el subtotal
            float subtotal = precioUnitario * cantidad;

            // Agregar los datos a la tabla tablaDetalles
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            Object[] row = {
                productoSeleccionado.getIdProducto(),
                productoSeleccionado.getNombreProducto(),
                precioUnitario,
                cantidad,
                subtotal
            };
            model.addRow(row);

            // Limpiar los campos después de agregar
            textCantidad.setText("");
            textPrecio.setText("");
            cargarProductos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionbtnAgregar

    private void accionBotonGuardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonGuardar
        // TODO add your handling code here:
        try {
            // Obtener el índice seleccionado de clientes y empleados
            int indiceCliente = comboClientes.getSelectedIndex();
            int indiceEmpleado = comboEmpleados.getSelectedIndex();
            if (indiceCliente < 0 || indiceEmpleado < 0) {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente y un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la lista de clientes y empleados
            List<Cliente> clientes = clienteControlador.obtenerTodosClientes();
            List<Empleado> empleados = empleadoControlador.obtenerTodosEmpleados();
            int idCliente = clientes.get(indiceCliente).getIdCliente();
            int idEmpleado = empleados.get(indiceEmpleado).getIdEmpleado();

            // Obtener la fecha seleccionada
            Date fechaVenta = selectorfechaVenta.getDate();
            if (fechaVenta == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener los detalles de la tabla tablaDetalles
            DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
            int rowCount = modelDetalles.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this, "Agregue al menos un producto a la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear lista de detalles y calcular total
            List<DetalleVenta> detalles = new ArrayList<>();
            float totalVenta = 0;
            for (int i = 0; i < rowCount; i++) {
                int idProducto = (int) modelDetalles.getValueAt(i, 0); // ID Producto como Integer
                float precioUnitario = ((Number) modelDetalles.getValueAt(i, 2)).floatValue(); // Precio Unitario como Float
                int cantidad = (int) modelDetalles.getValueAt(i, 3); // Cantidad como Integer
                float subtotal = ((Number) modelDetalles.getValueAt(i, 4)).floatValue(); // Subtotal como Float

                // Crear objeto DetalleVenta
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdProducto(idProducto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(precioUnitario);
                detalles.add(detalle);

                totalVenta += subtotal;
            }

            // Crear y guardar la venta
            ventaControlador.crearVenta(idCliente, idEmpleado, fechaVenta, totalVenta, detalles);

            limpiar();

            // Recargar la tabla de ventas
            cargarDatosTablaVentas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_accionBotonGuardar

    private void accionbtnLimpiar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnLimpiar
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_accionbtnLimpiar

    private void textBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyTyped
        // TODO add your handling code here:
        String textoBusqueda = textBuscar.getText().trim().toLowerCase();
        List<Venta> ventas = ventaControlador.obtenerTodasVentas();

        DefaultTableModel modelo = (DefaultTableModel) tablaVentas.getModel();
        modelo.setRowCount(0);

        if (ventas != null) {
            for (Venta v : ventas) {
                Cliente cliente = clienteControlador.obtenerClientePorId(v.getIdCliente());
                String nombreCliente = cliente.getPrimerNombre() + " " + cliente.getPrimerApellido();

                Empleado empleado = empleadoControlador.obtenerEmpleadoPorId(v.getIdEmpleado());
                String nombreEmpleado = empleado.getPrimerNombre() + " " + empleado.getPrimerApellido();

                if (textoBusqueda.isEmpty()
                        || nombreCliente.toLowerCase().contains(textoBusqueda)
                        || nombreEmpleado.toLowerCase().contains(textoBusqueda)) {
                    Object[] fila = {
                        v.getIdVenta(),
                        v.getFechaVenta(),
                        nombreCliente,
                        nombreEmpleado,
                        v.getTotalVenta()
                    };
                    modelo.addRow(fila);
                }
            }
        }
    }//GEN-LAST:event_textBuscarKeyTyped

    private void tablasVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablasVentasMouseClicked
        // Verificar si es un doble clic
        if (evt.getClickCount() == 2) {
            try {
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);

                // Obtener el índice de la fila seleccionada en tablaVentas
                int filaSeleccionada = tablaVentas.getSelectedRow();
                if (filaSeleccionada == -1) {
                    return; // No hacer nada si no hay fila seleccionada
                }

                // Obtener el idVenta de la fila seleccionada
                DefaultTableModel modelVentas = (DefaultTableModel) tablaVentas.getModel();
                int idVenta = (int) modelVentas.getValueAt(filaSeleccionada, 0);

                // Obtener la venta seleccionada para acceder a sus datos
                List<Venta> ventas = ventaControlador.obtenerTodasVentas();
                Venta ventaSeleccionada = null;
                for (Venta v : ventas) {
                    if (v.getIdVenta() == idVenta) {
                        ventaSeleccionada = v;
                        break;
                    }
                }
                if (ventaSeleccionada == null) {
                    JOptionPane.showMessageDialog(this, "Venta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cargar cliente en comboClientes
                List<Cliente> clientes = clienteControlador.obtenerTodosClientes();
                int indiceCliente = -1;
                for (int i = 0; i < clientes.size(); i++) {
                    if (clientes.get(i).getIdCliente() == ventaSeleccionada.getIdCliente()) {
                        indiceCliente = i;
                        break;
                    }
                }
                if (indiceCliente != -1) {
                    idClienteSeleccionado = ventaSeleccionada.getIdCliente();
                    comboClientes.setSelectedIndex(indiceCliente);
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Cargar empleado en comboEmpleados
                List<Empleado> empleados = empleadoControlador.obtenerTodosEmpleados();
                int indiceEmpleado = -1;
                for (int i = 0; i < empleados.size(); i++) {
                    if (empleados.get(i).getIdEmpleado() == ventaSeleccionada.getIdEmpleado()) {
                        indiceEmpleado = i;
                        break;
                    }
                }
                if (indiceEmpleado != -1) {
                    idEmpleadoSeleccionado = ventaSeleccionada.getIdEmpleado();
                    comboEmpleados.setSelectedIndex(indiceEmpleado);
                } else {
                    JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Detener el timer actual
                if (timer != null) {
                    timer.stop();
                }

                // Asignar la hora al label
                horabd = true;
                java.text.SimpleDateFormat horaFormato = new java.text.SimpleDateFormat("HH:mm:ss");
                String horaVenta = horaFormato.format(ventaSeleccionada.getFechaVenta());
                hora.setText(horaVenta); // Ajusta 'horaLabel' al nombre real de tu JLabel

                // Cargar la fecha en selectorfechaContratacion
                selectorfechaVenta.setDate(ventaSeleccionada.getFechaVenta());

                // Limpiar y cargar los detalles en tablaDetalles
                DefaultTableModel modelDetalles = (DefaultTableModel) tablaDetalles.getModel();
                modelDetalles.setRowCount(0);

                List<DetalleVenta> detalles = detalleVentaControlador.obtenerTodosDetallesVenta();
                if (detalles != null) {
                    for (DetalleVenta detalle : detalles) {
                        if (detalle.getIdVenta() == idVenta) {
                            Producto producto = productoControlador.obtenerProductoPorId(detalle.getIdProducto());
                            String nombreProducto = (producto != null) ? producto.getNombreProducto() : "Desconocido";

                            Object[] row = {
                                detalle.getIdProducto(),
                                nombreProducto,
                                detalle.getPrecioUnitario(),
                                detalle.getCantidad(),
                                detalle.getPrecioUnitario() * detalle.getCantidad() // Subtotal
                            };
                            modelDetalles.addRow(row);
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos de la venta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_tablasVentasMouseClicked

    private void accionbtnQuitarDetalles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionbtnQuitarDetalles
        // TODO add your handling code here:
        try {
            // Obtener el índice de la fila seleccionada en tablaDetalles
            int filaSeleccionada = tablaDetalles.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un detalle para quitar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Eliminar la fila seleccionada del modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
            model.removeRow(filaSeleccionada);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al quitar el detalle: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_accionbtnQuitarDetalles

    private void accionBotonGenerarReporte(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionBotonGenerarReporte
        // TODO add your handling code here:
       try {
            FileDialog dialogoArchivo = new FileDialog((java.awt.Frame) null, "Guardar Reporte PDF",FileDialog.SAVE);
            dialogoArchivo.setFile("ReportesVentas.pdf");
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
        
        documento.add(new Paragraph("Reportes de Ventas")
        .setTextAlignment(TextAlignment.CENTER)
        .setFontSize(20)
        .setBold());
        
        documento.add(new Paragraph("Fecha:" + new java.util.Date().toString())
        .setTextAlignment(TextAlignment.CENTER)
        .setFontSize(12));
        
        Table tabla = new Table(5);
        tabla.setWidth(UnitValue.createPercentValue(100));
        tabla.addHeaderCell("ID Venta").setBold();
        tabla.addHeaderCell("id_cliente").setBold();
        tabla.addHeaderCell("id_empleado").setBold();
        tabla.addHeaderCell("fecha_venta").setBold();
        tabla.addHeaderCell("total_venta").setBold();
        
        List<Venta> listaVentas =
        ventaControlador.obtenerTodasVentas();
        if (listaVentas != null){
            for (Venta venta : listaVentas){
                tabla.addCell(String.valueOf(venta.getIdVenta()));
                tabla.addCell(String.valueOf(venta.getIdCliente()));
                tabla.addCell(String.valueOf(venta.getIdEmpleado()));
                tabla.addCell(String.valueOf(venta.getFechaVenta()));
                tabla.addCell(String.valueOf(venta.getTotalVenta()));
                
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
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnQuitarDetalle;
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JComboBox<String> comboEmpleados;
    private javax.swing.JComboBox<String> comboProductos;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jlabel1;
    private javax.swing.JLabel jlabel2;
    private com.toedter.calendar.JDateChooser selectorfechaVenta;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textCantidad;
    private javax.swing.JTextField textPrecio;
    // End of variables declaration//GEN-END:variables
}
