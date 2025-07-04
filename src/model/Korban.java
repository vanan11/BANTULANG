package model;

public class Korban {
    private int id;
    private String nama;
    private String alamat;
    private String status;
    private String tanggal;
    private String bantuan;

    public Korban(int id, String nama, String alamat, String status, String tanggal, String bantuan) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.status = status;
        this.tanggal = tanggal;
        this.bantuan = bantuan;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getStatus() { return status; }
    public String getTanggal() { return tanggal; }
    public String getBantuan() { return bantuan; }

    public void setNama(String nama) { this.nama = nama; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public void setStatus(String status) { this.status = status; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public void setBantuan(String bantuan) { this.bantuan = bantuan; }
}
