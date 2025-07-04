package model;

public class Korban extends Orang {
    private String status;
    private String tanggal;
    private double bantuan;

    public Korban(String nama, String alamat, String tanggal, String status, double bantuan) {
        super(nama, alamat);
        this.tanggal = tanggal;
        this.status = status;
        this.bantuan = bantuan;
    }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getBantuan() { return bantuan; }
    public void setBantuan(double bantuan) { this.bantuan = bantuan; }
}
