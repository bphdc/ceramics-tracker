package entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.sql.Timestamp;

/**
 * A class to represent a user
 *
 * @author pete
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "user_id")
    private int id;

    private String username;

    private String email;

    private String bio;
    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "password_hash")
    private String password;

    private String role; // "admin" or "user"
    @Column(name = "created_at")
    private Timestamp createdAt;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Parameterized constructor to initialize a User object.
     *
     * @param id             Unique identifier for the user.
     * @param username       The username of the user.
     * @param email          The user's email address.
     * @param bio            A short bio describing the user.
     * @param profilePicture URL or path to the user's profile picture.
     * @param password       Password hash for user
     * @param role           The role of the user (either "admin" or "user").
     * @param createdAt      The timestamp when the user was created.
     */
    public User(int id, String username, String email, String bio, String profilePicture, String password, String role, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    /**
     * Gets the user ID.
     *
     * @return The user'sid.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id The user'sid.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email of the user.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the bio of the user.
     *
     * @return The user's bio.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the bio of the user.
     *
     * @param bio The bio to set.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Gets the profile picture of the user.
     *
     * @return The URL or path of the profile picture.
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the profile picture of the user.
     *
     * @param profilePicture The URL or path of the profile picture to set.
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Gets the pass hash of the user.
     *
     * @return  pass hash
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the pass hash of the user.
     *
     * @param password pass hash
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     *
     * @return The user's role (either "admin" or "user").
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role The role to set (either "admin" or "user").
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the creation timestamp of the user.
     *
     * @return The timestamp when the user was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the user.
     *
     * @param createdAt The timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string containing user details.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}