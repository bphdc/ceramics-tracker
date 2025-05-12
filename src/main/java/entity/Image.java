package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

/**
 * The type Images.
 */
@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "image_id")
    private int imageId;

    @NotNull(message = "Project must not be null")
    @ManyToOne
    @JoinColumn(name ="project_id", nullable = false)
    private Project project;

    @URL(message = "Image url must be valid")
    @Column(name = "image_url")
    private String imageUrl;

    @NotNull(message = "Created timestamp must not be null")
    @PastOrPresent(message = "Created timestamp must be in the past or present")
    @Column(name = "uploaded_at")
    private Timestamp uploadedAt;

    /**
     * Instantiates a new Images.
     */
    public Image() {
    }

    /**
     * Instantiates a new Images.
     *
     * @param imageId    the image id
     * @param project    the project
     * @param imageUrl   the image url
     * @param uploadedAt the uploaded at
     */
    public Image(int imageId, Project project, String imageUrl, Timestamp uploadedAt) {
        this();
        this.imageId = imageId;
        this.project = project;
        this.imageUrl = imageUrl;
        this.uploadedAt = uploadedAt;
    }

    /**
     * Gets image id.
     *
     * @return the image id
     */
    public int getImageId() {
        return imageId;
    }

    /**
     * Sets image id.
     *
     * @param imageId the image id
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
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
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets uploaded at.
     *
     * @return the uploaded at
     */
    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    /**
     * Sets uploaded at.
     *
     * @param uploadedAt the uploaded at
     */
    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public String toString() {
        return "Images{" +
                "imageId=" + imageId +
                ", imageUrl='" + imageUrl + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}
