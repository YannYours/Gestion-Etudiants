package model;

public class Student {
    private int id;
    private String nom;
    private String prenom;

    public Student(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String toCSV() {
        return id + "," + nom + "," + prenom;
    }

    public static Student fromCSV(String line) {
        String[] parts = line.split(",");
        return new Student(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2]
        );
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nom: " + nom + " | Prénom: " + prenom;
    }
}
