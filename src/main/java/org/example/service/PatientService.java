package org.example.service;

import org.example.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    // Список для хранения пациентов
    private List<Patient> patients = new ArrayList<>();

    // Метод для добавления пациента
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Метод для поиска пациентов по диагнозу
    public List<Patient> findPatientsByDiagnosis(String diagnosis) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                result.add(patient);
            }
        }
        return result;
    }

    // Метод для поиска пациентов по диапазону номеров медицинских карт
    public List<Patient> findPatientsByMedicalCardRange(int start, int end) {
        List<Patient> result = new ArrayList<>();
        for (Patient patient : patients) {
            int medicalCardNumber = Integer.parseInt(patient.getMedicalCardNumber());
            if (medicalCardNumber >= start && medicalCardNumber <= end) {
                result.add(patient);
            }
        }
        return result;
    }
}
