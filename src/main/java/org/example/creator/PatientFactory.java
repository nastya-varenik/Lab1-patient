package org.example.creator;

import org.example.entity.Patient;
import org.example.validator.PatientValidator;

public class PatientFactory {
    public static Patient createPatient(Long id, String lastName, String firstName, String middleName,
                                        String address, String phone, String medicalCardNumber, String diagnosis) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        // Проверяем валидность данных
        if (!PatientValidator.isValidPatientData(id, lastName, firstName, middleName, address, phone, medicalCardNumber, diagnosis)) {
            throw new IllegalArgumentException("Invalid patient data.");
        }

        return new Patient(id, lastName, firstName, middleName, address, phone, medicalCardNumber, diagnosis);
    }
}
