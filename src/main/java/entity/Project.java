package entity;

import java.sql.Timestamp;

/**
 * A class to represent a project
 *
 * @author pete
 */
public class Project {
    private int projectId;
    private int userId; // The ID of the user who created the project
    private String name;
    private String description;
    private Timestamp createdAt;

    /**
     * Default constructor for Project.
     */
    public Project() {
    }

    /**
     * Parameterized constructor to initialize a Project object.
     *
     * @param projectId   Unique identifier for the project.
     * @param userId      The ID of the user who created the project.
     * @param name        The name of the project.
     * @param description A brief description of the project.
     * @param createdAt   The timestamp when the project was created.
     */
    public Project(int projectId, int userId, String name, String description, Timestamp createdAt) {
        this.projectId = projectId;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    /**
     * Gets the project ID.
     *
     * @return The project's unique identifier.
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Sets the project ID.
     *
     * @param projectId The project's unique identifier.
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the user ID of the project's creator.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID of the project's creator.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the name of the project.
     *
     * @return The project name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the project.
     *
     * @param name The project name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the project.
     *
     * @return The project description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the project.
     *
     * @param description The project description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the creation timestamp of the project.
     *
     * @return The timestamp when the project was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the project.
     *
     * @param createdAt The timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns a string representation of the Project object.
     *
     * @return A string containing project details.
     */
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
