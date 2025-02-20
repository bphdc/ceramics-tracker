package persistence;

import entity.Glaze;
import entity.Tag;
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
 * Test class for glaze dao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GlazeDaoTest {

    private static GenericDao<Glaze> dao;
    private static SessionFactory sessionFactory;
    private Glaze testResource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(Glaze.class);
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
        testResource = new Glaze("glaze name", "short description", "Underglaze", Timestamp.from(Instant.now()));

        //insert
        testResource.setGlazeId(dao.insert(testResource));
        assertEquals(2, testResource.getGlazeId(), "id should be 2 after insertion!");
        Glaze insertedResource = dao.getById(testResource.getGlazeId());
        assertNotNull(insertedResource, "new resource should be retrievable");
        assertEquals("glaze name", insertedResource.getName(), "name should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving tag added by insertion
        testResource = dao.getById(1);
        assertNotNull(testResource, "resource should be able to be found by id");
        assertEquals("Test Glaze", testResource.getName(), "name should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {
        testResource = dao.getById(1);
        testResource.setName("updated name");
        dao.saveOrUpdate(testResource);

        Glaze updatedResource = dao.getById(testResource.getGlazeId());
        assertNotNull(updatedResource, "updated resource shouldn't be null");
        assertEquals("updated name", testResource.getName(), "resource should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteResource() {
        testResource = dao.getById(1);
        dao.delete(testResource);
        Glaze deletedResource = dao.getById(testResource.getGlazeId());
        assertNull(deletedResource, "resource should be deleted");
    }

    /**
     * Get all check
     */
    @Test
    void testGetAllResources() {
        testResource = dao.getById(1);
        List<Glaze> glazes = dao.getAll();
        assertFalse(glazes.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testResource = dao.getById(1);
        List<Glaze> glazes = dao.getByPropertyEqual("name", "Test Glaze");
        assertFalse(glazes.isEmpty(), "should return at least one resource");
        assertEquals("Test Glaze", glazes.get(0).getName(), "value should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testResource = dao.getById(1);
        List<Glaze> glazes = dao.getByPropertyLike("name", "Test Glaze");
        assertFalse(glazes.isEmpty(), "should return users with 'test' in resource");
        assertTrue(glazes.get(0).getName().contains("Test"), "value should contain 'test'");
    }
}
