package com.blingo.lingdyo;

import com.blingo.lingdyo.annotations.ValidEmail;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String pswd;
    private String name;
    private String lastname;
    private Integer age = 0;
    private String description;
    @ValidEmail
    private String email;
    private String native_tonge;

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPswd() { return pswd; }
    public void setPswd(String pswd) { this.pswd = pswd; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNative_tonge() { return native_tonge; }
    public void setNative_tonge(String native_tonge) { this.native_tonge = native_tonge; }
}