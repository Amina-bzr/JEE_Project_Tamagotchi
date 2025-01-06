package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOwner", nullable = false)
    private Integer idOwner; // Renamed to idOwner

    @Column(name = "fname", nullable = false, length = 45)
    private String fname;

    @Column(name = "lname", nullable = false, length = 45)
    private String lname;

    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username; // Unique username for each owner

    // Constructors
    public Owner(String fname, String lname, String username) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
    }

    public Owner() {
    }

    // Getters and Setters
    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // toString method for better readability
    @Override
    public String toString() {
        return "Owner [idOwner=" + idOwner + ", fname=" + fname + ", lname=" + lname + ", username=" + username + "]";
    }
}
