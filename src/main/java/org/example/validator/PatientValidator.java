package org.example.validator;

import java.util.regex.Pattern;

public class PatientValidator {
    private static final String ID_REGEX = "^[0-9]{5}$"; // ID должен быть числом из 5 цифр
    private static final String PHONE_REGEX = "^[+]?[0-9]{10,15}$"; // Телефонный номер
    private static final String MEDICAL_CARD_REGEX = "^[0-9]{8}$"; // Номер медицинской карты

    private static final Pattern ID_PATTERN = Pattern.compile(ID_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern MEDICAL_CARD_PATTERN = Pattern.compile(MEDICAL_CARD_REGEX);

    public static boolean isValidId(String id) {
        return id != null && ID_PATTERN.matcher(id).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidMedicalCard(String medicalCardNumber) {
        return medicalCardNumber != null && MEDICAL_CARD_PATTERN.matcher(medicalCardNumber).matches();
    }
}
