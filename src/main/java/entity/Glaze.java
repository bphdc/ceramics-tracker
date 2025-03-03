package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a glaze
 *
 * @author pete
 */
@Entity
@Table(name="glazes")
public class Glaze {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "glaze_id")
    private int glazeId;
    private String name;
    private String description;
    private String type; // "underglaze" or "overglaze"
    @Column(name= "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "glaze", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectGlaze> projects = new ArrayList<>();

    /**
     * Instantiates a new Glaze.
     */
    public Glaze() {
    }

    /**
     * Instantiates a new Glaze.
     *
     * @param name        the name
     * @param description the description
     * @param type        the type
     * @param createdAt   the created at

     */
    public Glaze(String name, String description, String type, Timestamp createdAt) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;

    }



    /**
     * Instantiates a new Glaze.
     *
     * @param glazeId     the glaze id
     * @param name        the name
     * @param description the description
     * @param type        the type
     * @param createdAt   the created at
     * @param projects    the projects
     */
    public Glaze(int glazeId, String name, String description, String type, Timestamp createdAt, List<ProjectGlaze> projects) {
        this.glazeId = glazeId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
        this.projects = projects;
    }

    /**
     * Gets glaze id.
     *
     * @return the glaze id
     */
    public int getGlazeId() {
        return glazeId;
    }

    /**
     * Sets glaze id.
     *
     * @param glazeId the glaze id
     */
    public void setGlazeId(int glazeId) {
        this.glazeId = glazeId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets projects.
     *
     * @return the projects
     */
    public List<ProjectGlaze> getProjects() {
        return projects;
    }

    /**
     * Sets projects.
     *
     * @param projects the projects
     */
    public void setProjects(List<ProjectGlaze> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Glaze{" +
                "glazeId=" + glazeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
