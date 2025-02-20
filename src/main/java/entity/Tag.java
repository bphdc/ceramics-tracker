package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a tag
 *
 * @author pete
 */
@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "tag_id")
    private int tagId;
    private String name;
    @Column(name="tag_id")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProjectTag> projects = new ArrayList<>();

    /**
     * Instantiates a new Tag.
     */
    public Tag() {
    }

    /**
     * Instantiates a new Tag.
     *
     * @param tagId     the tag id
     * @param name      the name
     * @param createdAt the created at
     * @param projects  the projects
     */
    public Tag(int tagId, String name, Timestamp createdAt, List<ProjectTag> projects) {
        this.tagId = tagId;
        this.name = name;
        this.createdAt = createdAt;
        this.projects = projects;
    }

    /**
     * Gets tag id.
     *
     * @return the tag id
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * Sets tag id.
     *
     * @param tagId the tag id
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
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
    public List<ProjectTag> getProjects() {
        return projects;
    }

    /**
     * Sets projects.
     *
     * @param projects the projects
     */
    public void setProjects(List<ProjectTag> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", projects=" + projects +
                '}';
    }
}
