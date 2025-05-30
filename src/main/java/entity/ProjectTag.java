package entity;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

/**
 * The type Project tag.
 */
@Entity
@Table(name = "project_tags")
public class ProjectTag {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    @NotNull(message = "Project must not be null")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    @NotNull(message = "Tag must not be null")
    private Tag tag;

    /**
     * Instantiates a new Project tag.
     */
    public ProjectTag() {
    }

    /**
     * Instantiates a new Project tag.
     *
     * @param project the project
     * @param tag     the tag
     */
    public ProjectTag(Project project, Tag tag) {
        this();
        this.project = project;
        this.tag = tag;
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
     * Gets tag.
     *
     * @return the tag
     */
    public Tag getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ProjectTag{" +
                ", tag=" + tag +
                '}';
    }
}
