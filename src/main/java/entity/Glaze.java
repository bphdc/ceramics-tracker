package entity;

import java.sql.Timestamp;

/**
 * A class to represent a glaze
 *
 * @author pete
 */
public class Glaze {
    private int glazeId;
    private String name;
    private String description;
    private String type; // "underglaze" or "overglaze"
    private Timestamp createdAt;

    /**
     * Default constructor for Glaze.
     */
    public Glaze() {
    }

    /**
     * Parameterized constructor to initialize a Glaze object.
     *
     * @param glazeId     Unique identifier for the glaze.
     * @param name        The name of the glaze.
     * @param description A detailed description of the glaze.
     * @param type        The type of glaze (either "underglaze" or "overglaze").
     * @param createdAt   The timestamp when the glaze was created.
     */
    public Glaze(int glazeId, String name, String description, String type, Timestamp createdAt) {
        this.glazeId = glazeId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.createdAt = createdAt;
    }

    /**
     * Gets the glaze ID.
     *
     * @return The glaze's unique identifier.
     */
    public int getGlazeId() {
        return glazeId;
    }

    /**
     * Sets the glaze ID.
     *
     * @param glazeId The glaze's unique identifier.
     */
    public void setGlazeId(int glazeId) {
        this.glazeId = glazeId;
    }

    /**
     * Gets the name of the glaze.
     *
     * @return The name of the glaze.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the glaze.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the glaze.
     *
     * @return The glaze description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the glaze.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the type of glaze.
     *
     * @return The type of glaze (either "underglaze" or "overglaze").
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of glaze.
     *
     * @param type The type to set (either "underglaze" or "overglaze").
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the creation timestamp of the glaze.
     *
     * @return The timestamp when the glaze was created.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the glaze.
     *
     * @param createdAt The timestamp to set.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns a string representation of the Glaze object.
     *
     * @return A string containing glaze details.
     */
    @Override
    public String toString() {
        return "Glaze{" +
                "glazeId=" + glazeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
