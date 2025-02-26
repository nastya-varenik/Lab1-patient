package org.example.entity;

public class Patient {
    private long id; // Изменено на long
    private String lastName;
    private String firstName;
    private String middleName;
    private String address;
    private String phone;
    private String medicalCardNumber;
    private String diagnosis;

    public Patient(long id, String lastName, String firstName, String middleName,
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
    public long getId() {
        return id; // Изменено на long
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    // Переопределение toString() для удобства вывода данных
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Patient{")
                .append("id=").append(id) // Изменено на long
                .append(", lastName='").append(lastName).append('\'')
                .append(", firstName='").append(firstName).append('\'')
                .append(", middleName='").append(middleName).append('\'')
                .append(", address='").append(address).append('\'')
                .append(", phone='").append(phone).append('\'')
                .append(", medicalCardNumber='").append(medicalCardNumber).append('\'')
                .append(", diagnosis='").append(diagnosis).append('\'')
                .append('}');
        return sb.toString();
    }
}