package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

/**
 * The type Project entries.
 */
@Entity
@Table(name="project_entries")
public class ProjectEntries {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "entry_id")
    private int id;

    @ManyToOne
    @JoinColumn(name ="project_id", nullable = false)
    private Project project;

    @Column(name = "entry_text")
    private String entryText;

    @Column(name = "created_at")
    private Timestamp createdAt;

    /**
     * Instantiates a new Project entries.
     */
    public ProjectEntries() {
    }

    /**
     * Instantiates a new Project entries.
     *
     * @param id        the id
     * @param project   the project
     * @param entryText the entry text
     * @param createdAt the created at
     */
    public ProjectEntries(int id, Project project, String entryText, Timestamp createdAt) {
        this.id = id;
        this.project = project;
        this.entryText = entryText;
        this.createdAt = createdAt;
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

    /**
     * Gets project.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets project.
     *
     * @param project the project
     */
    public void setProject(Project project) {
        this.project = project;
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

    @Override
    public String toString() {
        return "ProjectEntries{" +
                "id=" + id +
                ", project=" + project +
                ", entryText='" + entryText + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
