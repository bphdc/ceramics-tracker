package entity;

import java.sql.Timestamp;

/**
 * The type Images.
 */
public class Images {


    private int image_id;

    private int project_id;

    private String image_url;

    private Timestamp uploadedAt;

    /**
     * Instantiates a new Images.
     */
    public Images() {
    }

    /**
     * Gets image id.
     *
     * @return the image id
     */
    public int getImage_id() {
        return image_id;
    }

    /**
     * Sets image id.
     *
     * @param image_id the image id
     */
    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    /**
     * Gets project id.
     *
     * @return the project id
     */
    public int getProject_id() {
        return project_id;
    }

    /**
     * Sets project id.
     *
     * @param project_id the project id
     */
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImage_url() {
        return image_url;
    }

    /**
     * Sets image url.
     *
     * @param image_url the image url
     */
    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
                "image_id=" + image_id +
                ", project_id=" + project_id +
                ", image_url='" + image_url + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }

    /**
     * Instantiates a new Images.
     *
     * @param project_id the project id
     * @param image_url  the image url
     * @param uploadedAt the uploaded at
     */
    public Images(int project_id, String image_url, Timestamp uploadedAt) {
        this.project_id = project_id;
        this.image_url = image_url;
        this.uploadedAt = uploadedAt;
    }
}
