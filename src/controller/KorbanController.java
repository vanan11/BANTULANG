package controller;

import java.util.ArrayList;
import model.Korban;

public class KorbanController {
    private ArrayList<Korban> daftarKorban = new ArrayList<>();

    public void tambahKorban(Korban korban) {
        daftarKorban.add(korban);
    }

    public ArrayList<Korban> getAllKorban() {
        return daftarKorban;
    }

    public Korban cariById(int id) {
        for (Korban k : daftarKorban) {
            if (k.getId() == id) return k;
        }
        return null;
    }

    public void hapusKorban(int id) {
        daftarKorban.removeIf(k -> k.getId() == id);
    }
}