package com.blingo.lingdyo;

import com.blingo.lingdyo.annotations.ValidEmail;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String pswd;
    private String name;
    private String lastname;
    private Integer age;
    private String description;
    @ValidEmail
    private String email;
    private String native_tonge;

    public User() {}

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPswd() { return pswd; }
    public void setPswd(String pswd) { this.pswd = pswd; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNative_tonge() { return native_tonge; }
    public void setNative_tonge(String native_tonge) { this.native_tonge = native_tonge; }
}