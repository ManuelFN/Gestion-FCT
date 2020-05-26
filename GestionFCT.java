import org.w3c.dom.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;
import java.util.Arrays;

class GestionFCT extends JFrame {
    JPanel panelInic = new JPanel(), botonesInic = new JPanel();
    JButton gEmpresa = new JButton("Gestión Empresas");
    JButton aAlumnos = new JButton("Asignación Alumnos");
    JButton fTabla = new JButton("Ficheros a Tabla");
    CardLayout espacioLaminado = new CardLayout();

    public GestionFCT() throws ClassNotFoundException, SQLException {

        ///////////////////////////////// PANEL INICIAL //////////////////////////////////////////

        JPanel panel_general_inicio = panelInicio();

        ///////////////////////////////// PANEL INICIAL //////////////////////////////////////////

        ///////////////////////////////// PANEL ASIGNACIÓN ALUMNOS //////////////////////////////////////////

        JPanel panel_general_aAlumno = panelAsignacionAlumno();

        ///////////////////////////////// PANEL ASIGNACIÓN ALUMNOS //////////////////////////////////////////

        ///////////////////////////////// PANEL GESTIÓN DE EMPRESAS //////////////////////////////////////////

        JPanel panel_general_gEmpresa = panelGestionEmpresas();

        ///////////////////////////////// PANEL GESTIÓN DE EMPRESAS //////////////////////////////////////////

        ///////////////////////////////// PANEL FICHEROS A TABLA //////////////////////////////////////////

        JPanel panel_general_ftabla = panelFicherosTabla();

        ///////////////////////////////// PANEL FICHEROS A TABLA //////////////////////////////////////////

        panelInic.setLayout(espacioLaminado);
        panelInic.add(panel_general_inicio, "1");
        panelInic.add(panel_general_gEmpresa, "2");
        panelInic.add(panel_general_aAlumno, "3");
        panelInic.add(panel_general_ftabla, "4");

        gEmpresa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                espacioLaminado.show(panelInic, "2");
            }
        });

        aAlumnos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                espacioLaminado.show(panelInic, "3");
            }
        });

        fTabla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                espacioLaminado.show(panelInic, "4");
            }
        });

        botonesInic.add(gEmpresa);
        botonesInic.add(aAlumnos);
        botonesInic.add(fTabla);
        add(panelInic, BorderLayout.NORTH);
        add(botonesInic, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel panelGestionEmpresas() throws ClassNotFoundException, SQLException {
        String[] TJornada = { "Partida", "Continua"};

        JTextField texto_codigo = new JTextField(6);
        JTextField texto_nombre = new JTextField(35);
        JTextField texto_CIF = new JTextField(10);
        JTextField texto_CP = new JTextField(5);
        JTextField texto_direccion = new JTextField(50);
        JTextField texto_localidad = new JTextField(20);
        JTextField texto_DNIresponsable = new JTextField(15);
        JTextField texto_nombreResponsable = new JTextField(20);
        JTextField texto_apellidosResponsable = new JTextField(35);
        JTextField texto_DNItutorLaboral = new JTextField(15);
        JTextField texto_nombreTutorLaboral = new JTextField(20);
        JTextField texto_apellidosTutorLaboral = new JTextField(20);
        JTextField texto_mailTutorLaboral = new JTextField(25);
        JTextField texto_tlfTutorLaboral = new JTextField(10);

        JButton boton_insertar = new JButton("Insertar");
        JButton boton_modificar = new JButton("Modificar");
        JButton boton_borrar = new JButton("Borrar");

        JPanel panel_general_gEmpresa = new JPanel();
        JPanel panel_titulo = new JPanel();
        JPanel panel2_gEmpresa = new JPanel();
        JPanel panel3_gEmpresa = new JPanel();
        JPanel panel4_gEmpresa = new JPanel();
        JPanel panel6_gEmpresa = new JPanel();
        JPanel panel7_gEmpresa = new JPanel();
        JPanel panel_botones = new JPanel();
        JPanel panel_tablaBBDD = new JPanel();

        JLabel label_titulo = new JLabel("GESTIÓN DE LAS EMPRESAS DEL PROGRAMA FCT");
        JLabel label_matricula = new JLabel("Código Empresa: ");
        JLabel label_nombre = new JLabel("Nombre Empresa: ");
        JLabel label_CIF = new JLabel("CIF: ");
        JLabel label_CP = new JLabel("C.P: ");
        JLabel label_direccion = new JLabel("Dirección: ");
        JLabel label_localidad = new JLabel("Localidad: ");
        JLabel label_tjornada = new JLabel("Tipo de Jornada: ");
        JLabel label_DNIresponsable = new JLabel("DNI del responsable: ");
        JLabel label_nombreResponsable = new JLabel("Nombre del responsable: ");
        JLabel label_apellidosResponsable = new JLabel("Apellidos del responsable: ");
        JLabel label_DNItutorLaboral = new JLabel("DNI del tutor laboral: ");
        JLabel label_nombreTutorLaboral = new JLabel("Nombre del tutor laboral: ");
        JLabel label_apellidosTutorLaboral = new JLabel("Apellidos del tutor laboral: ");
        JLabel label_mailTutorLaboral = new JLabel("Mail del tutor laboral: ");
        JLabel label_tlfTutorLaboral = new JLabel("Teléfono del tutor laboral: ");
        JLabel label_estado = new JLabel();

        JComboBox tipo_jornada = new JComboBox(TJornada);

        panel_general_gEmpresa.add(panel_titulo);
        panel_titulo.add(label_titulo);
        panel_titulo.setBorder(new EmptyBorder(10,0,0,0));

        panel_general_gEmpresa.add(panel2_gEmpresa);
        panel2_gEmpresa.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2_gEmpresa.setBorder(new EmptyBorder(0,400,5,5));
        panel2_gEmpresa.add(label_matricula);
        panel2_gEmpresa.add(texto_codigo);
        panel2_gEmpresa.add(label_nombre);
        panel2_gEmpresa.add(texto_nombre);

        panel_general_gEmpresa.add(panel3_gEmpresa);
        panel3_gEmpresa.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3_gEmpresa.setBorder(new EmptyBorder(0,400,5,5));
        panel3_gEmpresa.add(label_CIF);
        panel3_gEmpresa.add(texto_CIF);
        panel3_gEmpresa.add(label_direccion);
        panel3_gEmpresa.add(texto_direccion);
        panel3_gEmpresa.add(label_CP);
        panel3_gEmpresa.add(texto_CP);
        panel3_gEmpresa.add(label_localidad);
        panel3_gEmpresa.add(texto_localidad);

        panel_general_gEmpresa.add(panel4_gEmpresa);
        panel4_gEmpresa.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4_gEmpresa.setBorder(new EmptyBorder(0,400,5,5));
        panel4_gEmpresa.add(label_tjornada);
        panel4_gEmpresa.add(tipo_jornada);
        panel4_gEmpresa.add(label_DNIresponsable);
        panel4_gEmpresa.add(texto_DNIresponsable);
        panel4_gEmpresa.add(label_nombreResponsable);
        panel4_gEmpresa.add(texto_nombreResponsable);
        panel4_gEmpresa.add(label_apellidosResponsable);
        panel4_gEmpresa.add(texto_apellidosResponsable);

        panel_general_gEmpresa.add(panel6_gEmpresa);
        panel6_gEmpresa.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel6_gEmpresa.setBorder(new EmptyBorder(0,400,5,5));
        panel6_gEmpresa.add(label_DNItutorLaboral);
        panel6_gEmpresa.add(texto_DNItutorLaboral);
        panel6_gEmpresa.add(label_nombreTutorLaboral);
        panel6_gEmpresa.add(texto_nombreTutorLaboral);

        panel_general_gEmpresa.add(panel7_gEmpresa);
        panel7_gEmpresa.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel7_gEmpresa.setBorder(new EmptyBorder(0,400,5,5));
        panel7_gEmpresa.add(label_apellidosTutorLaboral);
        panel7_gEmpresa.add(texto_apellidosTutorLaboral);
        panel7_gEmpresa.add(label_mailTutorLaboral);
        panel7_gEmpresa.add(texto_mailTutorLaboral);
        panel7_gEmpresa.add(label_tlfTutorLaboral);
        panel7_gEmpresa.add(texto_tlfTutorLaboral);

        panel_general_gEmpresa.add(panel_botones);
        panel_botones.add(boton_insertar);
        panel_botones.add(boton_modificar);
        panel_botones.add(boton_borrar);
        panel_botones.add(label_estado);

        ///////////// Tabla /////////////

        // Hacemos que las celdas de la tabla no sean editables
        DefaultTableModel modelo = new DefaultTableModel(new String[] {"Cod.Empresa", "Nombre Empresa", "CIF", "Dirección", "CP", "Localidad", "Tipo de Jornada", "DNI Responsable", "Nombre Responsable", "Apellidos Responsable", "DNI Tutor Laboral", "Nombre Tutor Laboral", "Apellidos Tutor Laboral", "Mail Tutor Laboral", "Teléfono Tutor Laboral"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        JScrollPane sp = new JScrollPane(tabla);

        Class.forName("org.mariadb.jdbc.Driver");
        String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
        Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");

        PreparedStatement ps = conexion_bd.prepareStatement("SELECT * FROM empresas");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            modelo.addRow(new Object[] {
                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
            });
        }

        panel_general_gEmpresa.add(panel_tablaBBDD);
        sp.setPreferredSize(new Dimension(800,100));
        panel_tablaBBDD.add(sp);

        ps.close();
        conexion_bd.close();

        ///////////// Tabla /////////////

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int j = tabla.getSelectedRow();
                String seleccionado = String.valueOf(modelo.getValueAt(j, 0));
                String seleccionado1 = String.valueOf(modelo.getValueAt(j, 1));
                String seleccionado2 = String.valueOf(modelo.getValueAt(j, 2));
                String seleccionado3 = String.valueOf(modelo.getValueAt(j, 3));
                String seleccionado4 = String.valueOf(modelo.getValueAt(j, 4));
                String seleccionado5 = String.valueOf(modelo.getValueAt(j, 5));
                String seleccionado6 = String.valueOf(modelo.getValueAt(j, 6));
                String seleccionado7 = String.valueOf(modelo.getValueAt(j, 7));
                String seleccionado8 = String.valueOf(modelo.getValueAt(j, 8));
                String seleccionado9 = String.valueOf(modelo.getValueAt(j, 9));
                String seleccionado10 = String.valueOf(modelo.getValueAt(j, 10));
                String seleccionado11 = String.valueOf(modelo.getValueAt(j, 11));
                String seleccionado12 = String.valueOf(modelo.getValueAt(j, 12));
                String seleccionado13 = String.valueOf(modelo.getValueAt(j, 13));
                String seleccionado14 = String.valueOf(modelo.getValueAt(j, 14));

                texto_codigo.setText(seleccionado);
                texto_nombre.setText(seleccionado1);
                texto_CIF.setText(seleccionado2);
                texto_direccion.setText(seleccionado3);
                texto_CP.setText(seleccionado4);
                texto_localidad.setText(seleccionado5);
                tipo_jornada.setSelectedItem(seleccionado6);
                texto_DNIresponsable.setText(seleccionado7);
                texto_nombreResponsable.setText(seleccionado8);
                texto_apellidosResponsable.setText(seleccionado9);
                texto_DNItutorLaboral.setText(seleccionado10);
                texto_nombreTutorLaboral.setText(seleccionado11);
                texto_apellidosTutorLaboral.setText(seleccionado12);
                texto_mailTutorLaboral.setText(seleccionado13);
                texto_tlfTutorLaboral.setText(seleccionado14);
            }
        });

        boton_insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.mariadb.jdbc.Driver");

                    String codigo_empresa = texto_codigo.getText();
                    String nombre_empresa = texto_nombre.getText();
                    String cif = texto_CIF.getText();
                    String direccion = texto_direccion.getText();
                    String cp = texto_CP.getText();
                    String localidad = texto_localidad.getText();
                    String jornada = tipo_jornada.getSelectedItem().toString();
                    String dni_responsable = texto_DNIresponsable.getText();
                    String nombre_responsable = texto_nombreResponsable.getText();
                    String apellidos_responsable = texto_apellidosResponsable.getText();
                    String dni_tutorLaboral = texto_DNItutorLaboral.getText();
                    String nombre_tutorLaboral = texto_nombreTutorLaboral.getText();
                    String apellidos_tutorLaboral = texto_apellidosTutorLaboral.getText();
                    String mail_tutorLaboral = texto_mailTutorLaboral.getText();
                    String tlf_tutorLaboral = texto_tlfTutorLaboral.getText();

                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";

                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");

                    PreparedStatement encapsulaCons = conexion_bd.prepareStatement("INSERT INTO EMPRESAS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    encapsulaCons.setString(1, codigo_empresa);
                    encapsulaCons.setString(2, nombre_empresa);
                    encapsulaCons.setString(3, cif);
                    encapsulaCons.setString(4, direccion);
                    encapsulaCons.setString(5, cp);
                    encapsulaCons.setString(6, localidad);
                    encapsulaCons.setString(7, jornada);
                    encapsulaCons.setString(8, dni_responsable);
                    encapsulaCons.setString(9, nombre_responsable);
                    encapsulaCons.setString(10, apellidos_responsable);
                    encapsulaCons.setString(11, dni_tutorLaboral);
                    encapsulaCons.setString(12, nombre_tutorLaboral);
                    encapsulaCons.setString(13, apellidos_tutorLaboral);
                    encapsulaCons.setString(14, mail_tutorLaboral);
                    encapsulaCons.setString(15, tlf_tutorLaboral);

                    int res = encapsulaCons.executeUpdate();

                    if (res > 0) {
                        label_estado.setText("Registro insertado");
                        panel_botones.add(label_estado);
                        setVisible(true);
                    }

                    else {
                        label_estado.setText("Error en la operación");
                        panel_botones.add(label_estado);
                        setVisible(true);
                    }

                    //Actualiza la tabla

                    PreparedStatement ps = conexion_bd.prepareStatement("SELECT * FROM empresas");
                    ResultSet rs = ps.executeQuery();

                    modelo.setRowCount(0);

                    while (rs.next()) {
                        modelo.addRow(new Object[] {
                                rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                        });
                    }

                    tabla.setModel(modelo);

                    encapsulaCons.close();
                    conexion_bd.close();
                }

                catch (Exception ex) {
                    label_estado.setText("Error en la operación");
                    panel_botones.add(label_estado);
                    setVisible(true);
                }
            }
        });

        boton_modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.mariadb.jdbc.Driver");

                    String codigo_empresa = texto_codigo.getText();
                    String nombre_empresa = texto_nombre.getText();
                    String cif = texto_CIF.getText();
                    String direccion = texto_direccion.getText();
                    String cp = texto_CP.getText();
                    String localidad = texto_localidad.getText();
                    String jornada = tipo_jornada.getSelectedItem().toString();
                    String dni_responsable = texto_DNIresponsable.getText();
                    String nombre_responsable = texto_nombreResponsable.getText();
                    String apellidos_responsable = texto_apellidosResponsable.getText();
                    String dni_tutorLaboral = texto_DNItutorLaboral.getText();
                    String nombre_tutorLaboral = texto_nombreTutorLaboral.getText();
                    String apellidos_tutorLaboral = texto_apellidosTutorLaboral.getText();
                    String mail_tutorLaboral = texto_mailTutorLaboral.getText();
                    String tlf_tutorLaboral = texto_tlfTutorLaboral.getText();

                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");
                    PreparedStatement encapsulaCons = conexion_bd.prepareStatement("UPDATE EMPRESAS SET NombreEmpresa=?, CIF=?, Direccion=?, CP=?, Localidad=?, TipoJornada=?, DNI_Responsable=?, Nombre_Responsable=?, Apellidos_Responsable=?, DNI_TutorLaboral=?, Nombre_TutorLaboral=?, Apellidos_TutorLaboral=?, Mail_TutorLaboral=?, Telefono_TutorLaboral=? WHERE CodigoEmpresa=?");

                    encapsulaCons.setString(1, nombre_empresa);
                    encapsulaCons.setString(2, cif);
                    encapsulaCons.setString(3, direccion);
                    encapsulaCons.setString(4, cp);
                    encapsulaCons.setString(5, localidad);
                    encapsulaCons.setString(6, jornada);
                    encapsulaCons.setString(7, dni_responsable);
                    encapsulaCons.setString(8, nombre_responsable);
                    encapsulaCons.setString(9, apellidos_responsable);
                    encapsulaCons.setString(10, dni_tutorLaboral);
                    encapsulaCons.setString(11, nombre_tutorLaboral);
                    encapsulaCons.setString(12, apellidos_tutorLaboral);
                    encapsulaCons.setString(13, mail_tutorLaboral);
                    encapsulaCons.setString(14, tlf_tutorLaboral);
                    encapsulaCons.setString(15, codigo_empresa);

                    int res = encapsulaCons.executeUpdate();

                    if (res > 0) {
                        label_estado.setText("Registro Modificado");
                        panel_botones.add(label_estado);
                        setVisible(true);
                    }

                    else {
                        label_estado.setText("Error en la operación");
                        panel_botones.add(label_estado);
                        setVisible(true);
                    }

                    //Actualiza la tabla

                    PreparedStatement ps = conexion_bd.prepareStatement("SELECT * FROM empresas");
                    ResultSet rs = ps.executeQuery();

                    modelo.setRowCount(0);

                    while (rs.next()) {
                        modelo.addRow(new Object[] {
                                rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                        });
                    }

                    tabla.setModel(modelo);

                    encapsulaCons.close();
                    conexion_bd.close();
                }

                catch (Exception ex) {
                    label_estado.setText("Error en la operación");
                    panel_botones.add(label_estado);
                    setVisible(true);
                }
            }
        });

        boton_borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.mariadb.jdbc.Driver");

                    String codigo_empresa = texto_codigo.getText();

                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");
                    PreparedStatement encapsulaCons = conexion_bd.prepareStatement("DELETE FROM EMPRESAS WHERE CodigoEmpresa=?");

                    encapsulaCons.setString(1, codigo_empresa);

                    int res = encapsulaCons.executeUpdate();

                    if (res > 0) {
                        label_estado.setText("Registro Borrado");
                        panel_botones.add(label_estado);
                        setVisible(true);
                    }

                    else {
                        label_estado.setText("Error en la operación");
                        panel_botones.add(label_estado);
                        setVisible(true);
                    }

                    //Actualiza la tabla

                    PreparedStatement ps = conexion_bd.prepareStatement("SELECT * FROM empresas");
                    ResultSet rs = ps.executeQuery();

                    modelo.setRowCount(0);

                    while (rs.next()) {
                        modelo.addRow(new Object[] {
                                rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
                        });
                    }

                    tabla.setModel(modelo);

                    encapsulaCons.close();
                    conexion_bd.close();
                }

                catch (Exception ex) {
                    System.out.println("Ha habido problemas: " + ex.getMessage());
                    label_estado.setText("Error en la operación");
                    panel_botones.add(label_estado);
                    setVisible(true);
                }

            }
        });

        panel_general_gEmpresa.setLayout(new GridLayout(13,1));
        return panel_general_gEmpresa;
    }

    private JPanel panelFicherosTabla() {
        JPanel panel_general_ftabla = new JPanel();
        JPanel panel_titulo_ftabla = new JPanel();
        JPanel panel_imagen_ftabla = new JPanel();
        JPanel panel_boton_ftabla = new JPanel();
        JPanel panel_estado_ftabla = new JPanel();

        JLabel label_texttitulo_ftabla = new JLabel("ALUMNOS Y TUTORES A TABLA");
        JLabel imagen_ftabla = new JLabel();
        JLabel label_estado_ftabla = new JLabel();

        JButton boton_ftabla = new JButton("Realizar la operación");

        panel_general_ftabla.add(panel_titulo_ftabla);
        panel_titulo_ftabla.add(label_texttitulo_ftabla);
        panel_titulo_ftabla.setBorder(new EmptyBorder(10,0,0,0));

        panel_general_ftabla.add(panel_imagen_ftabla);
        panel_imagen_ftabla.add(imagen_ftabla);
        imagen_ftabla.setIcon(new ImageIcon(GestionFCT.class.getResource("imagenes/img_2.png")));

        panel_general_ftabla.add(panel_boton_ftabla);
        panel_boton_ftabla.add(boton_ftabla);
        panel_boton_ftabla.setBorder(new EmptyBorder(10,0,0,0));

        panel_general_ftabla.add(panel_estado_ftabla);
        panel_boton_ftabla.add(label_estado_ftabla);
        panel_boton_ftabla.setBorder(new EmptyBorder(0,0,0,0));

        panel_general_ftabla.setLayout(new GridLayout(5,1));

        boton_ftabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    InputStream ficheroXML = GestionFCT.class.getResourceAsStream("ficheros/tutoresSAFA.xml");

                    //Fichero XML a la tabla TUTORES de la Base de Datos

                    Class.forName("org.mariadb.jdbc.Driver");
                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");

                    PreparedStatement ps = conexion_bd.prepareStatement("INSERT INTO TUTORES VALUES(?,?,?,?)");

                    DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newDefaultInstance();
                    DocumentBuilder constructDoc = factoriaDoc.newDocumentBuilder();
                    Document doc = constructDoc.parse(ficheroXML);

                    doc.getDocumentElement().normalize();

                    NodeList tutores = doc.getElementsByTagName("tutordoc");
                    for (int cont = 0; cont < tutores.getLength(); cont++) {
                        Node nodo = tutores.item(cont);
                        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) nodo;

                            ps.setString(1, element.getElementsByTagName("codtut").item(0).getTextContent());
                            ps.setString(2, element.getElementsByTagName("nomap").item(0).getTextContent());
                            ps.setString(3, element.getElementsByTagName("correo").item(0).getTextContent());
                            ps.setString(4, element.getElementsByTagName("telefono").item(0).getTextContent());

                            ps.executeUpdate();
                        }
                    }

                    int result = ps.executeUpdate();

                    if (result > 0) {
                        label_estado_ftabla.setText("Ficheros insertados a tabla");
                        panel_estado_ftabla.add(label_estado_ftabla);
                        setVisible(true);
                    } else {
                        label_estado_ftabla.setText("Error");
                        panel_estado_ftabla.add(label_estado_ftabla);
                        setVisible(true);
                    }

                    ps.close();
                    conexion_bd.close();
                }

                catch (Exception ex) {
                    label_estado_ftabla.setText("Ficheros insertados a tabla");
                    panel_estado_ftabla.add(label_estado_ftabla);
                    setVisible(true);
                }

                try {

                    //Fichero .dat a la tabla ALUMNOS de la Base de Datos

                    Class.forName("org.mariadb.jdbc.Driver");
                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");

                    InputStream input = GestionFCT.class.getResourceAsStream("ficheros/alumnos2CFS.dat");
                    int dat_length = input.available();
                    DataInputStream datainput = new DataInputStream(input);

                    PreparedStatement ps_dat = conexion_bd.prepareStatement("INSERT INTO ALUMNOS VALUES(?,?,?,?,?)");

                    for (int i = 0; i < dat_length; i++) {
                        ps_dat.setString(1, String.valueOf(datainput.readInt()));
                        ps_dat.setString(2, datainput.readUTF());
                        ps_dat.setString(3, datainput.readUTF());
                        ps_dat.setString(4, datainput.readUTF());
                        ps_dat.setString(5, datainput.readUTF());

                        ps_dat.executeUpdate();
                    }

                    ps_dat.close();
                    conexion_bd.close();
                }

                catch (Exception ex) {
                    label_estado_ftabla.setText("Ficheros insertados a tabla");
                    panel_estado_ftabla.add(label_estado_ftabla);
                    setVisible(true);
                }
            }
        });

        return panel_general_ftabla;
    }

    private JPanel panelAsignacionAlumno() throws ClassNotFoundException, SQLException {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel_general_aAlumno = new JPanel();

        JLabel etiqueta = new JLabel("ASIGNACION DE ALUMNOS A EMPRESAS");
        JLabel alumno = new JLabel("Elección de Alumno");
        JLabel empresa = new JLabel("Elección de Empresa");
        JLabel tutor = new JLabel("Elección de Tutor");

        JTextPane texto = new JTextPane();
        texto.setPreferredSize(new Dimension(500,60));
        texto.setEditable(false);
        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        texto.setParagraphAttributes(attribs, true);
        texto.setBackground(Color.LIGHT_GRAY);

        JComboBox jcombox_tutores = new JComboBox();
        JComboBox jcombox_empresas = new JComboBox();
        JComboBox jcombox_alumnos = new JComboBox();

        Class.forName("org.mariadb.jdbc.Driver");
        String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
        Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");

        PreparedStatement ps_tutores = conexion_bd.prepareStatement("SELECT * FROM tutores");
        PreparedStatement ps_empresas = conexion_bd.prepareStatement("SELECT * FROM empresas");
        PreparedStatement ps_alumnos = conexion_bd.prepareStatement("SELECT * FROM alumnos");

        ResultSet rs = ps_tutores.executeQuery();
        ResultSet rs2 = ps_empresas.executeQuery();
        ResultSet rs3 = ps_alumnos.executeQuery();

        while (rs.next()) {
            jcombox_tutores.addItem(rs.getString(2));
        }

        while (rs2.next()) {
            jcombox_empresas.addItem(rs2.getString(2));
        }

        while (rs3.next()) {
            jcombox_alumnos.addItem(rs3.getString(3) + " " + rs3.getString(4));
        }

        ps_alumnos.close();
        ps_tutores.close();
        ps_empresas.close();
        conexion_bd.close();

        JButton boton_asignar = new JButton("Asignar");
        JButton boton_actualizar = new JButton("Actualizar Datos");

        panel1.add(etiqueta);
        panel2.add(alumno);
        panel2.add(jcombox_alumnos);
        panel3.add(empresa);
        panel3.add(jcombox_empresas);
        panel4.add(tutor);
        panel4.add(jcombox_tutores);
        panel5.add(texto);
        panel6.add(boton_asignar);
        panel6.add(boton_actualizar);

        boton_asignar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("org.mariadb.jdbc.Driver");

                    String tutores = jcombox_tutores.getSelectedItem().toString();
                    String empresas = jcombox_empresas.getSelectedItem().toString();
                    String alumnos = jcombox_alumnos.getSelectedItem().toString();

                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");
                    PreparedStatement ps = conexion_bd.prepareStatement("INSERT INTO ASIGNADOS VALUES(?,?,?)");

                    ps.setString(1, alumnos);
                    ps.setString(2, empresas);
                    ps.setString(3, tutores);

                    ps.executeUpdate();

                    ps.close();
                    conexion_bd.close();

                    texto.setText("El alumno " + jcombox_alumnos.getSelectedItem() + " queda asignado a la empresa " + jcombox_empresas.getSelectedItem() + " supervisados por el tutor " + jcombox_tutores.getSelectedItem() + " (docente)");
                }

                catch (Exception ex) {
                    System.out.println("Ha habido problemas: " + ex.getMessage());
                }
            }
        });

        boton_actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jcombox_tutores.removeAllItems();
                    jcombox_empresas.removeAllItems();
                    jcombox_alumnos.removeAllItems();

                    Class.forName("org.mariadb.jdbc.Driver");
                    String url_conexion = "jdbc:mariadb://localhost:3306/Proy3TE3";
                    Connection conexion_bd = DriverManager.getConnection(url_conexion, "root", "");

                    PreparedStatement ps_tutores = conexion_bd.prepareStatement("SELECT * FROM tutores");
                    PreparedStatement ps_empresas = conexion_bd.prepareStatement("SELECT * FROM empresas");
                    PreparedStatement ps_alumnos = conexion_bd.prepareStatement("SELECT * FROM alumnos");

                    ResultSet rs = ps_tutores.executeQuery();
                    ResultSet rs2 = ps_empresas.executeQuery();
                    ResultSet rs3 = ps_alumnos.executeQuery();

                    while (rs.next()) {
                        jcombox_tutores.addItem(rs.getString(2));
                    }

                    while (rs2.next()) {
                        jcombox_empresas.addItem(rs2.getString(2));
                    }

                    while (rs3.next()) {
                        jcombox_alumnos.addItem(rs3.getString(3) + " " + rs3.getString(4));
                    }

                    ps_alumnos.close();
                    ps_tutores.close();
                    ps_empresas.close();
                    conexion_bd.close();
                }

                catch (Exception ex) {

                }
            }
        });

        panel1.setBorder(new EmptyBorder(5,10,5,1));

        panel_general_aAlumno.add(panel1);
        panel_general_aAlumno.add(panel2);
        panel_general_aAlumno.add(panel3);
        panel_general_aAlumno.add(panel4);
        panel_general_aAlumno.add(panel5);
        panel_general_aAlumno.add(panel6);
        panel_general_aAlumno.add(panel7);

        panel_general_aAlumno.setLayout(new GridLayout(10,1));
        return panel_general_aAlumno;
    }

    private JPanel panelInicio() {
        JPanel panel_general_inicio = new JPanel();
        JPanel panel_titulo_inicio = new JPanel();
        JPanel panel_imagen_inicio = new JPanel();

        JLabel label_texttitulo_inicio = new JLabel("GESTIÓN SAFA FCT");
        JLabel logo_safa = new JLabel();

        panel_general_inicio.add(panel_titulo_inicio);
        panel_titulo_inicio.add(label_texttitulo_inicio);
        panel_titulo_inicio.setBorder(new EmptyBorder(10,0,0,0));

        panel_general_inicio.add(panel_imagen_inicio);
        panel_imagen_inicio.setBorder(new EmptyBorder(0,0,0,0));
        panel_imagen_inicio.add(logo_safa);
        logo_safa.setIcon(new ImageIcon(GestionFCT.class.getResource("imagenes/nuevo_logo_SAFA_H.png")));

        panel_general_inicio.setLayout(new GridLayout(5,1));
        return panel_general_inicio;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GestionFCT marco = new GestionFCT();
        marco.setSize(1920, 1000);
        marco.setTitle("SAFA-Ntra. Sra. de los Reyes");
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);
    }
}
