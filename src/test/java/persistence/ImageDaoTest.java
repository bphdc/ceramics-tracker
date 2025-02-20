package persistence;

import entity.Glaze;
import entity.Image;
import entity.Project;
import entity.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import util.Database;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ImageDao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImageDaoTest {

    private static GenericDao<Image> dao;
    private static SessionFactory sessionFactory;
    private Image testResource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(Image.class);
    }

    /**
     * create a test resource before each test
     */
    @BeforeEach
    void setupEach() {
        Database database = Database.getInstance();
        //reset db
        database.runSQL("cleanDB.sql");

        //create one user using sql
        database.runSQL("createTestResources.sql");


    }


    /**
     * Create check
     */
    @Test
    void testInsertResource() {
        //create data for test tag
        GenericDao<Project> projectDao = new GenericDao<>(Project.class);
        Project projectToAdd = projectDao.getById(1);
        testResource = new Image(0, projectToAdd, "www.imageurl.com", Timestamp.from(Instant.now()));

        //insert
        testResource.setImageId(dao.insert(testResource));
        assertEquals(2, testResource.getImageId(), "id should be 2 after insertion!");
        Image insertedResource = dao.getById(testResource.getImageId());
        assertNotNull(insertedResource, "new resource should be retrievable");
        assertEquals("www.imageurl.com", insertedResource.getImageUrl(), "name should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving tag added by insertion
        testResource = dao.getById(1);
        assertNotNull(testResource, "resource should be able to be found by id");
        assertEquals("https://example.com/images/project1.jpg", testResource.getImageUrl(), "name should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {
        testResource = dao.getById(1);
        testResource.setImageUrl("www.updatedImageUrl.com");
        dao.saveOrUpdate(testResource);

        Image updatedResource = dao.getById(testResource.getImageId());
        assertNotNull(updatedResource, "updated resource shouldn't be null");
        assertEquals("www.updatedImageUrl.com", testResource.getImageUrl(), "resource should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteResource() {
        testResource = dao.getById(1);
        dao.delete(testResource);
        Image deletedResource = dao.getById(testResource.getImageId());
        assertNull(deletedResource, "resource should be deleted");
    }

    /**
     * Get all check
     */
    @Test
    void testGetAllResources() {
        testResource = dao.getById(1);
        List<Image> resourceList = dao.getAll();
        assertFalse(resourceList.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testResource = dao.getById(1);
        List<Image> resourceList = dao.getByPropertyEqual("imageUrl", "https://example.com/images/project1.jpg");
        assertFalse(resourceList.isEmpty(), "should return at least one resource");
        assertEquals("https://example.com/images/project1.jpg", resourceList.get(0).getImageUrl(), "value should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testResource = dao.getById(1);
        List<Image> resourceList = dao.getByPropertyLike("imageUrl", "https://example.com/images/project1.jpg");
        assertFalse(resourceList.isEmpty(), "should return users with 'example' in resource");
        assertTrue(resourceList.get(0).getImageUrl().contains("example"), "value should contain 'example'");
    }
}
