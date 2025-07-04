package view;

import controller.KorbanController;
import model.Korban;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class InputKorbanFrame extends JFrame {
    JTextField tfNama, tfAlamat, tfTanggal, tfBantuan;
    JComboBox<String> cbStatus;
    KorbanController controller;
    DefaultTableModel tableModel;
    JTable table;

    public InputKorbanFrame() {
        controller = new KorbanController();

        setTitle("INPUT KORBAN - BANTULANG");
        setSize(420, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel l1 = new JLabel("Nama:");
        JLabel l2 = new JLabel("Alamat:");
        JLabel l3 = new JLabel("Tanggal:");
        JLabel l4 = new JLabel("Status:");
        JLabel l5 = new JLabel("Bantuan:");

        tfNama = new JTextField();
        tfAlamat = new JTextField();
        tfTanggal = new JTextField();
        cbStatus = new JComboBox<>(new String[]{"SELESAI", "BELUM SELESAI"});
        tfBantuan = new JTextField();

        JButton btnSimpan = new JButton("SIMPAN");

        l1.setBounds(30, 30, 80, 25); tfNama.setBounds(130, 30, 230, 25);
        l2.setBounds(30, 70, 80, 25); tfAlamat.setBounds(130, 70, 230, 25);
        l3.setBounds(30, 110, 80, 25); tfTanggal.setBounds(130, 110, 230, 25);
        l4.setBounds(30, 150, 80, 25); cbStatus.setBounds(130, 150, 230, 25);
        l5.setBounds(30, 190, 80, 25); tfBantuan.setBounds(130, 190, 230, 25);
        btnSimpan.setBounds(130, 230, 100, 30);

        add(l1); add(tfNama);
        add(l2); add(tfAlamat);
        add(l3); add(tfTanggal);
        add(l4); add(cbStatus);
        add(l5); add(tfBantuan);
        add(btnSimpan);

        // Tabel Korban
        String[] kolom = {"ID", "Nama", "Alamat", "Status"};
        tableModel = new DefaultTableModel(kolom, 0);
        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBounds(30, 280, 330, 180);
        add(tableScroll);

        // Pewarnaan kolom status
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (column == 3) { // kolom STATUS
                    String status = table.getValueAt(row, column).toString();
                    if (status.equalsIgnoreCase("SELESAI")) {
                        c.setForeground(Color.GREEN.darker());
                    } else {
                        c.setForeground(Color.RED);
                    }
                } else {
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });

        // Tombol SIMPAN
        btnSimpan.addActionListener(e -> {
            try {
                String nama = tfNama.getText();
                String alamat = tfAlamat.getText();
                String tanggal = tfTanggal.getText();
                String status = cbStatus.getSelectedItem().toString();
                double bantuan = Double.parseDouble(tfBantuan.getText());

                Korban k = new Korban(nama, alamat, tanggal, status, bantuan);
                controller.tambah(k);

                int id = controller.getAll().size() + 1010; // Mulai dari 1011
                tableModel.addRow(new Object[]{
                        id, nama, alamat, status
                });

                // Reset form
                tfNama.setText("");
                tfAlamat.setText("");
                tfTanggal.setText("");
                tfBantuan.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Input tidak valid!");
            }
        });

        setVisible(true);
    }
}
