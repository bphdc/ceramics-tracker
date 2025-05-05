package entity;

import com.mysql.cj.protocol.x.XMessage;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

/**
 * The type Project entries.
 */
@Entity
@Table(name="project_entries")
public class ProjectEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "entry_id")
    private int id;

    @NotNull(message = "project must not be null")
    @ManyToOne
    @JoinColumn(name ="project_id", nullable = false)
    private Project project;

    @NotBlank(message = "entry can't be blank")
    @Length(max = 5000, message = "Description must be at most 5000 characters")
    @Column(name = "entry_text")
    private String entryText;

    @NotNull(message = "Created timestamp must not be null")
    @PastOrPresent(message = "Created timestamp must be in the past or present")
    @Column(name = "created_at")
    private Timestamp createdAt;

    /**
     * Instantiates a new Project entries.
     */
    public ProjectEntry() {
    }

    /**
     * Instantiates a new Project entries.
     *
     * @param id        the id
     * @param project   the project
     * @param entryText the entry text
     * @param createdAt the created at
     */
    public ProjectEntry(int id, Project project, String entryText, Timestamp createdAt) {
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
                ", entryText='" + entryText + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
