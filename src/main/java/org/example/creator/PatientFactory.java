package org.example.creator;

import org.example.entity.Patient;
import org.example.validator.PatientValidator;

public class PatientFactory {

    public static Patient createPatient(String id, String lastName, String firstName, String middleName,
                                        String address, String phone, String medicalCardNumber, String diagnosis) {
        if (PatientValidator.isValidId(id) && PatientValidator.isValidPhone(phone) && PatientValidator.isValidMedicalCard(medicalCardNumber)) {
            return new Patient(id, lastName, firstName, middleName, address, phone, medicalCardNumber, diagnosis);
        } else {
            throw new IllegalArgumentException("Invalid patient data.");
        }
    }
}
