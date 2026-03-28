package com.chess.database.Class;

import java.time.LocalDate;

// LỚP NÀY LƯU CÁC THÔNG TIN VỀ NGƯỜI DÙNG
public class User {
    private int userID;
    private String userName; // email
    private String passwordHash; // mật khấu luôn được băm trước
    private String fullName; // họ và tên
    private String gender; //giới tính
    private LocalDate dateOfBirth; // yyyy-mm-dd 

    // dùng để chứa dữ liệu từ database
    public User(int userID, String userName, String passwordHash, String fullName, String gender, LocalDate dateOfBirth) {
        this.userID = userID;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    // GETTER
    public long getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    // SETTER
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userName=" + userName + ", passwordHash=" + passwordHash + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + '}';
    }    
    
}
