package org.example.entity;

public class Patient {
    private String id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String phone;
    private String medicalCardNumber;
    private String diagnosis;

    public Patient(String id, String lastName, String firstName, String middleName,
                   String address, String phone, String medicalCardNumber, String diagnosis) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.phone = phone;
        this.medicalCardNumber = medicalCardNumber;
        this.diagnosis = diagnosis;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getMedicalCardNumber() { return medicalCardNumber; }
    public String getDiagnosis() { return diagnosis; }

    // Переопределение toString() для удобства вывода данных
    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", medicalCardNumber='" + medicalCardNumber + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
