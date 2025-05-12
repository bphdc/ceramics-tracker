package entity;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

/**
 * The type Project glaze.
 */
@Entity
@Table(name = "project_glazes")
public class ProjectGlaze {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    @NotNull(message = "Project must not be null")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "glaze_id")
    @NotNull(message = "Glaze must not be null")
    private Glaze glaze;

    /**
     * Instantiates a new Project glaze.
     */
    public ProjectGlaze() {
    }

    /**
     * Instantiates a new Project glaze.
     *
     * @param project the project
     * @param glaze   the glaze
     */
    public ProjectGlaze(Project project, Glaze glaze) {
        this();
        this.project = project;
        this.glaze = glaze;
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
     * Gets glaze.
     *
     * @return the glaze
     */
    public Glaze getGlaze() {
        return glaze;
    }

    /**
     * Sets glaze.
     *
     * @param glaze the glaze
     */
    public void setGlaze(Glaze glaze) {
        this.glaze = glaze;
    }

    @Override
    public String toString() {
        return "ProjectGlaze{" +
                ", glaze=" + glaze +
                '}';
    }


}
