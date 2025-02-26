package org.example;

import org.example.creator.PatientFactory;
import org.example.entity.Patient;
import org.example.service.PatientService;

public class Main {
    public static void main(String[] args) {
        try {
            // Создание сервиса пациентов
            PatientService patientService = new PatientService();

            // Создание пациентов
            Patient patient1 = PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu");
            Patient patient2 = PatientFactory.createPatient("12346", "Petrov", "Petr", "Petrovich", "Moscow", "+79161234568", "10000002", "Cold");
            Patient patient3 = PatientFactory.createPatient("12347", "Sidorov", "Sidor", "Sidorovich", "Moscow", "+79161234569", "10000003", "Flu");

            // Добавление пациентов в сервис
            patientService.addPatient(patient1);
            patientService.addPatient(patient2);
            patientService.addPatient(patient3);

            // Поиск пациентов по диагнозу
            System.out.println("Patients with Flu diagnosis:");
            System.out.println(patientService.findPatientsByDiagnosis("Flu"));

            // Поиск пациентов по диапазону номеров мед. карт
            System.out.println("Patients with medical card number between 10000001 and 10000002:");
            System.out.println(patientService.findPatientsByMedicalCardRange(10000001, 10000002));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
