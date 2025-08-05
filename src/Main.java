import model.Student;
import service.StudentManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager mgr = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- Gestion Étudiants ---");
            System.out.println("1. Lister les étudiants");
            System.out.println("2. Ajouter un étudiant");
            System.out.println("3. Consulter un étudiant");
            System.out.println("4. Modifier un étudiant");
            System.out.println("5. Supprimer un étudiant");
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");
            choix = sc.nextInt();
            sc.nextLine();  // consomme la fin de ligne

            switch (choix) {
                case 1:
                    mgr.listStudents();
                    break;
                case 2:
                    System.out.print("ID: "); int newId = sc.nextInt(); sc.nextLine();
                    System.out.print("Nom: "); String nom = sc.nextLine();
                    System.out.print("Prénom: "); String prenom = sc.nextLine();
                    mgr.addStudent(new Student(newId, nom, prenom));
                    System.out.println("Étudiant ajouté.");
                    break;
                case 3:
                    System.out.print("ID de l'étudiant: ");
                    Student s1 = mgr.findById(sc.nextInt());
                    System.out.println(s1 != null ? s1 : "Étudiant introuvable.");
                    break;
                case 4:
                    System.out.print("ID à modifier: ");
                    int idMod = sc.nextInt(); sc.nextLine();
                    Student s2 = mgr.findById(idMod);
                    if (s2 != null) {
                        System.out.print("Nouveau nom: ");
                        s2.setNom(sc.nextLine());
                        System.out.print("Nouveau prénom: ");
                        s2.setPrenom(sc.nextLine());
                        mgr.updateStudent(s2);
                        System.out.println("Données modifiées.");
                    } else {
                        System.out.println("Étudiant introuvable.");
                    }
                    break;
                case 5:
                    System.out.print("ID à supprimer: ");
                    mgr.deleteStudent(sc.nextInt());
                    System.out.println("Étudiant supprimé si existant.");
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);

        sc.close();
    }
}
