package view;

import controller.KorbanController;
import model.Korban;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class InputKorbanFrame extends JFrame {
    private JTextField txtNama, txtAlamat, txtTanggal, txtBantuan;
    private JTable table;
    private DefaultTableModel model;
    private KorbanController controller = new KorbanController();
    private int currentId = 1011;

    public InputKorbanFrame() {
        setTitle("INPUT KORBAN - BANTULANG");
        setSize(700, 600);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        JLabel lblAlamat = new JLabel("Alamat:");
        JLabel lblTanggal = new JLabel("Tanggal:");
        JLabel lblBantuan = new JLabel("Bantuan:");

        txtNama = new JTextField();
        txtAlamat = new JTextField();
        txtTanggal = new JTextField();
        txtBantuan = new JTextField();

        JButton btnSimpan = new JButton("SIMPAN");
        JButton btnEdit = new JButton("EDIT");
        JButton btnHapus = new JButton("HAPUS");

        model = new DefaultTableModel(new Object[]{"ID", "Nama", "Alamat", "Status", "Tanggal", "Bantuan"}, 0);
        table = new JTable(model);

        // Pewarnaan status otomatis
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                            boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = table.getValueAt(row, 3).toString();
                if (column == 3) {
                    if (status.equalsIgnoreCase("Selesai")) {
                        c.setForeground(Color.GREEN.darker());
                    } else if (status.equalsIgnoreCase("Belum Selesai")) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);

        lblNama.setBounds(20, 20, 100, 25);
        txtNama.setBounds(130, 20, 200, 25);
        lblAlamat.setBounds(20, 60, 100, 25);
        txtAlamat.setBounds(130, 60, 200, 25);
        lblTanggal.setBounds(20, 100, 100, 25);
        txtTanggal.setBounds(130, 100, 200, 25);
        lblBantuan.setBounds(20, 140, 100, 25);
        txtBantuan.setBounds(130, 140, 200, 25);

        btnSimpan.setBounds(130, 180, 90, 30);
        btnEdit.setBounds(230, 180, 90, 30);
        btnHapus.setBounds(330, 180, 90, 30);

        scrollPane.setBounds(20, 230, 650, 300);

        add(lblNama); add(txtNama);
        add(lblAlamat); add(txtAlamat);
        add(lblTanggal); add(txtTanggal);
        add(lblBantuan); add(txtBantuan);
        add(btnSimpan); add(btnEdit); add(btnHapus);
        add(scrollPane);

        btnSimpan.addActionListener(e -> {
            try {
                String nama = txtNama.getText();
                String alamat = txtAlamat.getText();
                String tanggal = txtTanggal.getText();
                double bantuanDouble = Double.parseDouble(txtBantuan.getText().replaceAll("[^0-9]", ""));
                String bantuanFormatted = NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(bantuanDouble);
                String status = bantuanDouble >= 5000000 ? "Selesai" : "Belum Selesai";

                Korban k = new Korban(currentId++, nama, alamat, status, tanggal, bantuanFormatted);
                controller.tambahKorban(k);
                loadTable();
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Masukkan bantuan dalam angka yang valid.");
            }
        });

        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                Korban k = controller.cariById(id);
                if (k != null) {
                    k.setNama(txtNama.getText());
                    k.setAlamat(txtAlamat.getText());
                    k.setTanggal(txtTanggal.getText());

                    double bantuanDouble = Double.parseDouble(txtBantuan.getText().replaceAll("[^0-9]", ""));
                    String bantuanFormatted = NumberFormat.getCurrencyInstance(new Locale("id", "ID")).format(bantuanDouble);
                    String status = bantuanDouble >= 5000000 ? "Selesai" : "Belum Selesai";

                    k.setBantuan(bantuanFormatted);
                    k.setStatus(status);
                    loadTable();
                    clearForm();
                }
            }
        });

        btnHapus.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                controller.hapusKorban(id);
                loadTable();
                clearForm();
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                txtNama.setText(table.getValueAt(row, 1).toString());
                txtAlamat.setText(table.getValueAt(row, 2).toString());
                txtTanggal.setText(table.getValueAt(row, 4).toString());
                txtBantuan.setText(table.getValueAt(row, 5).toString().replace("Rp", "").replace(".", ""));
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        for (Korban k : controller.getAllKorban()) {
            model.addRow(new Object[]{k.getId(), k.getNama(), k.getAlamat(), k.getStatus(), k.getTanggal(), k.getBantuan()});
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtTanggal.setText("");
        txtBantuan.setText("");
    }
}
