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


}
