package entity;

import java.sql.Timestamp;

/**
 * The type Project entries.
 */
public class ProjectEntries {

    private int id;

    private int projectId;

    private String entryText;

    private Timestamp createdAt;

    /**
     * Instantiates a new Project entries.
     */
    public ProjectEntries() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProjectEntries{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", entryText='" + entryText + '\'' +
                ", createdAt=" + createdAt +
                '}';
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
     * Gets entry text.
     *
     * @return the entry text
     */
    public String getEntryText() {
        return entryText;
    }

    /**
     * Sets entry text.
     *
     * @param entryText the entry text
     */
    public void setEntryText(String entryText) {
        this.entryText = entryText;
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
     * Instantiates a new Project entries.
     *
     * @param projectId the project id
     * @param entryText the entry text
     * @param createdAt the created at
     */
    public ProjectEntries(int projectId, String entryText, Timestamp createdAt) {
        this.projectId = projectId;
        this.entryText = entryText;
        this.createdAt = createdAt;
    }
}
