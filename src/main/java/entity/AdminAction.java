package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;


/**
 * The type Admin action.
 */
@Entity
@Table(name="admin_actions")
public class AdminAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "action_id")
    private int id;

    @ManyToOne
    @JoinColumn(name ="admin_id", nullable = false)
    private User user;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "target_id")
    private int targetId;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    /**
     * Instantiates a new Admin action.
     */
    public AdminAction() {
    }

    /**
     * Instantiates a new Admin action.
     *
     * @param user       the user
     * @param actionType the action type
     * @param targetId   the target id
     * @param timestamp   time stamp
     */
    public AdminAction(User user, String actionType, int targetId, Timestamp timestamp) {
        this.user = user;
        this.actionType = actionType;
        this.targetId = targetId;
        this.timestamp =timestamp;
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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * Sets action type.
     *
     * @param actionType the action type
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * Gets target id.
     *
     * @return the target id
     */
    public int getTargetId() {
        return targetId;
    }

    /**
     * Sets target id.
     *
     * @param targetId the target id
     */
    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AdminAction{" +
                "id=" + id +
                ", actionType='" + actionType + '\'' +
                ", targetId=" + targetId +
                ", timestamp=" + timestamp +
                '}';
    }
}
