package controller;

import model.Korban;
import java.util.ArrayList;

public class KorbanController {
    private ArrayList<Korban> data = new ArrayList<>();

    public void tambah(Korban k) {
        data.add(k);
    }

    public void update(int index, Korban k) {
        data.set(index, k);
    }

    public void hapus(int index) {
        data.remove(index);
    }

    public ArrayList<Korban> getAll() {
        return data;
    }
}
