package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a project
 *
 * @author pete
 */
@Entity
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "project_id")
    private int projectId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectEntry> projectEntries = new ArrayList<>();

    //these are the many to manny links
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectGlaze> glazes = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectTag> tags = new ArrayList<>();

    /**
     * Default constructor for Project.
     */
    public Project() {
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectId      the project id
     * @param user           the user
     * @param name           the name
     * @param description    the description
     * @param createdAt      the created at
     */
    public Project(int projectId, User user, String name, String description, Timestamp createdAt) {
        this.projectId = projectId;
        this.user = user;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    /**
     * Instantiates a new Project.
     *
     * @param projectId      the project id
     * @param user           the user
     * @param name           the name
     * @param description    the description
     * @param createdAt      the created at
     * @param images         the images
     * @param projectEntries the project entries
     * @param glazes         the glazes
     * @param tags           the tags
     */
    public Project(int projectId, User user, String name, String description, Timestamp createdAt, List<Image> images, List<ProjectEntry> projectEntries, List<ProjectGlaze> glazes, List<ProjectTag> tags) {
        this.projectId = projectId;
        this.user = user;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.images = images;
        this.projectEntries = projectEntries;
        this.glazes = glazes;
        this.tags = tags;
    }

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets project id.
     *
     * @param projectId the project id
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
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
     * Gets images.
     *
     * @return the images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * Gets project entries.
     *
     * @return the project entries
     */
    public List<ProjectEntry> getProjectEntries() {
        return projectEntries;
    }

    /**
     * Sets project entries.
     *
     * @param projectEntries the project entries
     */
    public void setProjectEntries(List<ProjectEntry> projectEntries) {
        this.projectEntries = projectEntries;
    }

    /**
     * Gets glazes.
     *
     * @return the glazes
     */
    public List<ProjectGlaze> getGlazes() {
        return glazes;
    }

    /**
     * Sets glazes.
     *
     * @param glazes the glazes
     */
    public void setGlazes(List<ProjectGlaze> glazes) {
        this.glazes = glazes;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<ProjectTag> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<ProjectTag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", images=" + images +
                ", projectEntries=" + projectEntries +
                ", glazes=" + glazes +
                ", tags=" + tags +
                '}';
    }
}