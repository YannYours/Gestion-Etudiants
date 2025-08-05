package service;

import model.Student;

import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_PATH = "data/students.csv";
    private List<Student> students = new ArrayList<>();

    public StudentManager() {
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromCSV(line));
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur écriture fichier : " + e.getMessage());
        }
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("Aucun étudiant enregistré.");
        } else {
            students.forEach(System.out::println);
        }
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    public Student findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == s.getId()) {
                students.set(i, s);
                saveToFile();
                return;
            }
        }
        System.out.println("Étudiant introuvable.");
    }

    public void deleteStudent(int id) {
        students.removeIf(s -> s.getId() == id);
        saveToFile();
    }
}
