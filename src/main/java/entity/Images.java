package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

/**
 * The type Images.
 */
@Entity
@Table(name="images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "image_id")
    private int imageId;

    @ManyToOne
    @JoinColumn(name ="project_id", nullable = false)
    private Project project;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "uploaded_at")
    private Timestamp uploadedAt;

    /**
     * Instantiates a new Images.
     */
    public Images() {
    }


}
