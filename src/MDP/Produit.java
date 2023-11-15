package MDP;

import java.time.LocalDate;

public class Produit {
    private long id;
    private String designation;
    private int qte;
    private double prix;
    private LocalDate date;
    private double total;

    public Produit(long id, String designation, int qte, double prix, LocalDate date) {
        this.id = id;
        this.designation = designation;
        this.qte = qte;
        this.prix = prix;
        this.date = date;
        calcTotal();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;

    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
        calcTotal();
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
        calcTotal();
    }

    public LocalDate getDate() {
        return date;
    }

    public double getTotal() {
        return total;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private void calcTotal() {
        total = prix * qte;
    }

    @Override
    public String toString() {
        return designation + "\t" + qte + "\t" + prix + "\t" + date;
    }
}
