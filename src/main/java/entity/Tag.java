package entity;

import java.sql.Timestamp;

/**
 * A class to represent a tag
 *
 * @author pete
 */
public class Tag {
    private int tagId;
    private String name;
    private Timestamp createdAt;

    /**
     * Default constructor for Tag.
     */
    public Tag() {
    }

    /**
     * Parameterized constructor to initialize a Tag object.
     *
     * @param tagId     Unique identifier for the tag.
     * @param name      The name of the tag.
     * @param createdAt The timestamp when the tag was created.
     */
    public Tag(int tagId, String name, Timestamp createdAt) {
        this.tagId = tagId;
        this.name = name;
        this.createdAt = createdAt;
    }

    /**
     * Gets the tag ID.
     *
     * @return The tag's unique identifier.
     */
    public int getTagId() {
        return tagId;
    }

    /**
     * Sets the tag ID.
     *
     * @param tagId The tag's unique identifier.
     */
    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    /**
     * Gets the name of the tag.
     *
     * @return The name of the tag.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tag.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the creation timestamp of the tag.
     *
     * @return The timestamp when the tag was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the tag.
     *
     * @param createdAt The timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns a string representation of the Tag object.
     *
     * @return A string containing tag details.
     */
    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
