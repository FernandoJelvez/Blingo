package com.blingo.lingdyo;

import com.blingo.lingdyo.annotations.ValidEmail;
import com.blingo.lingdyo.annotations.ValidUsername;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ValidUsername
    private String username;
    private String pswd;
    private String name;
    private String lastname;
    private Integer age;
    private String description;
    @ValidEmail
    private String email;
    private String native_tonge;
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany
    private List<Course> courses;

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

    public List<Course> getCourses(){
        return courses;
    }
    public void setCourses(List<Course> courses){
        this.courses = courses;
    }

    public boolean compareEditableDetails(User other) {
        if (other == null) return false;

        return Objects.equals(name, other.name) &&
                Objects.equals(lastname, other.lastname) &&
                Objects.equals(email, other.email) &&
                Objects.equals(age, other.age) &&
                Objects.equals(native_tonge, other.native_tonge);
    }
}