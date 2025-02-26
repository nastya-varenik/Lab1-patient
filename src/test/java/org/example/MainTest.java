package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.creator.PatientFactory;
import org.example.entity.Patient;
import org.example.service.PatientService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.List;

public class MainTest {

    private static final Logger logger = LogManager.getLogger(MainTest.class);
    private PatientService patientService;

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up PatientService for a new test");
        patientService = new PatientService();
    }

    // 1. Тест на создание пациента с корректными данными
    @Test
    public void testCreatePatientValid() {
        logger.info("Starting testCreatePatientValid");
        // Given
        String id = "12345";
        String lastName = "Ivanov";
        String firstName = "Ivan";
        String middleName = "Ivanovich";
        String address = "Moscow";
        String phone = "+79161234567";
        String medicalCardNumber = "10000001";
        String diagnosis = "Flu";

        // When
        Patient patient = PatientFactory.createPatient(id, lastName, firstName, middleName, address, phone, medicalCardNumber, diagnosis);

        // Then
        assertNotNull(patient);
        assertEquals(patient.getId(), id);
        assertEquals(patient.getLastName(), lastName);
        assertEquals(patient.getFirstName(), firstName);
        assertEquals(patient.getMiddleName(), middleName);
        assertEquals(patient.getAddress(), address);
        assertEquals(patient.getPhone(), phone);
        assertEquals(patient.getMedicalCardNumber(), medicalCardNumber);
        assertEquals(patient.getDiagnosis(), diagnosis);
        logger.info("Patient created successfully with ID: {}", id);
    }

    // 2. Тест на поиск пациентов по диагнозу
    @Test
    public void testSearchPatientsByDiagnosis() {
        logger.info("Starting testSearchPatientsByDiagnosis");
        // Given
        Patient patient1 = PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu");
        Patient patient2 = PatientFactory.createPatient("12346", "Petrov", "Petr", "Petrovich", "Moscow", "+79161234568", "10000002", "Cold");
        Patient patient3 = PatientFactory.createPatient("12347", "Sidorov", "Sidor", "Sidorovich", "Moscow", "+79161234569", "10000003", "Flu");

        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
        patientService.addPatient(patient3);

        // When
        List<Patient> result = patientService.findPatientsByDiagnosis("Flu");

        // Then
        assertEquals(result.size(), 2);
        assertTrue(result.contains(patient1));
        assertTrue(result.contains(patient3));
        assertFalse(result.contains(patient2)); // Убедимся, что пациент с другим диагнозом не попал в результат
        logger.info("Found {} patients with diagnosis 'Flu'", result.size());
    }

    // 3. Тест на поиск пациентов по диапазону номеров медицинских карт
    @Test
    public void testSearchPatientsByMedicalCardRange() {
        logger.info("Starting testSearchPatientsByMedicalCardRange");
        // Given
        Patient patient1 = PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu");
        Patient patient2 = PatientFactory.createPatient("12346", "Petrov", "Petr", "Petrovich", "Moscow", "+79161234568", "10000002", "Cold");
        Patient patient3 = PatientFactory.createPatient("12347", "Sidorov", "Sidor", "Sidorovich", "Moscow", "+79161234569", "10000003", "Flu");

        patientService.addPatient(patient1);
        patientService.addPatient(patient2);
        patientService.addPatient(patient3);

        // When
        List<Patient> result = patientService.findPatientsByMedicalCardRange(10000001, 10000002);

        // Then
        assertEquals(result.size(), 2);
        assertTrue(result.contains(patient1));
        assertTrue(result.contains(patient2));
        assertFalse(result.contains(patient3)); // Убедимся, что пациент за пределами диапазона не попал в результат
        logger.info("Found {} patients within the medical card range", result.size());
    }

    // 4. Тест на добавление пациента с некорректным номером медицинской карты
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatePatientWithInvalidMedicalCard() {
        logger.info("Starting testCreatePatientWithInvalidMedicalCard");
        // Given
        String medicalCardNumber = "abc"; // Некорректный номер карты

        // When
        PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", medicalCardNumber, "Flu");
    }

    // 5. Тест на добавление пациента с некорректным телефоном
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatePatientWithInvalidPhone() {
        logger.info("Starting testCreatePatientWithInvalidPhone");
        // Given
        String phone = "invalid-phone"; // Некорректный номер телефона

        // When
        PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", phone, "10000001", "Flu");
    }

    // 6. Тест на создание пациента с некорректным ID
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatePatientWithInvalidId() {
        logger.info("Starting testCreatePatientWithInvalidId");
        // Given
        String id = "id123"; // Некорректный ID

        // When
        PatientFactory.createPatient(id, "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu");
    }

    // 7. Тест на пустой список при поиске по диагнозу
    @Test
    public void testSearchPatientsByDiagnosisEmpty() {
        logger.info("Starting testSearchPatientsByDiagnosisEmpty");
        // Given
        patientService.addPatient(PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu"));

        // When
        List<Patient> result = patientService.findPatientsByDiagnosis("Cold");

        // Then
        assertTrue(result.isEmpty());
        logger.info("No patients found with diagnosis 'Cold'");
    }

    // 8. Тест на пустой список при поиске по диапазону номеров карт
    @Test
    public void testSearchPatientsByMedicalCardRangeEmpty() {
        logger.info("Starting testSearchPatientsByMedicalCardRangeEmpty");
        // Given
        patientService.addPatient(PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu"));

        // When
        List<Patient> result = patientService.findPatientsByMedicalCardRange(10000002, 10000003);

        // Then
        assertTrue(result.isEmpty());
        logger.info("No patients found within the medical card range");
    }

    // 9. Тест на создание пациента с пустым диагнозом
    @Test
    public void testCreatePatientWithEmptyDiagnosis() {
        logger.info("Starting testCreatePatientWithEmptyDiagnosis");
        // Given
        String diagnosis = ""; // Пустой диагноз

        // When
        Patient patient = PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", diagnosis);

        // Then
        assertNotNull(patient);
        assertEquals(patient.getDiagnosis(), diagnosis);
        logger.info("Patient created successfully with empty diagnosis");
    }

    // 10. Тест на поиск пациентов с пустым списком пациентов
    @Test
    public void testSearchPatientsWithEmptyList() {
        logger.info("Starting testSearchPatientsWithEmptyList");
        // Given
        // Список пациентов пуст

        // When
        List<Patient> resultByDiagnosis = patientService.findPatientsByDiagnosis("Flu");
        List<Patient> resultByMedicalCardRange = patientService.findPatientsByMedicalCardRange(10000001, 10000002);

        // Then
        assertTrue(resultByDiagnosis.isEmpty());
        assertTrue(resultByMedicalCardRange.isEmpty());
        logger.info("No patients found in the empty list");
    }

    // 11. Тест на добавление пациента с null значениями
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreatePatientWithNullValues() {
        logger.info("Starting testCreatePatientWithNullValues");
        // Given
        String id = null;
        String lastName = null;
        String firstName = null;
        String middleName = null;
        String address = null;
        String phone = null;
        String medicalCardNumber = null;
        String diagnosis = null;

        // When
        PatientFactory.createPatient(id, lastName, firstName, middleName, address, phone, medicalCardNumber, diagnosis);
    }

    // 12. Тест на поиск пациентов с некорректным диапазоном номеров карт (начало > конца)
    @Test
    public void testSearchPatientsByInvalidMedicalCardRange() {
        logger.info("Starting testSearchPatientsByInvalidMedicalCardRange");
        // Given
        patientService.addPatient(PatientFactory.createPatient("12345", "Ivanov", "Ivan", "Ivanovich", "Moscow", "+79161234567", "10000001", "Flu"));

        // When
        List<Patient> result = patientService.findPatientsByMedicalCardRange(10000002, 10000001); // Некорректный диапазон

        // Then
        assertTrue(result.isEmpty());
        logger.info("No patients found with invalid medical card range");
    }
}