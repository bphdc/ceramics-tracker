package persistence;

import entity.AdminAction;
import entity.Project;
import entity.ProjectEntry;
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
 * Test class for UserDao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminActionDaoTest {

    private static GenericDao<AdminAction> dao;
    private static SessionFactory sessionFactory;
    private AdminAction testResource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(AdminAction.class);
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
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User userToAdd = userDao.getById(1);
        testResource = new AdminAction(userToAdd, "Add Glaze", 1, Timestamp.from(Instant.now()));

        //insert
        testResource.setId(dao.insert(testResource));
        assertEquals(2, testResource.getId(), "id should be 2 after insertion!");
        AdminAction insertedResource = dao.getById(testResource.getId());
        assertNotNull(insertedResource, "new resource should be retrievable");
        assertEquals("Add Glaze", insertedResource.getActionType(), "value should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving tag added by insertion
        testResource = dao.getById(1);
        assertNotNull(testResource, "resource should be able to be found by id");
        assertEquals("Add Glaze", testResource.getActionType(), "value should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {
        testResource = dao.getById(1);
        testResource.setActionType("Delete Glaze");
        dao.saveOrUpdate(testResource);

        AdminAction updatedResource = dao.getById(testResource.getId());
        assertNotNull(updatedResource, "updated resource shouldn't be null");
        assertEquals("Delete Glaze", testResource.getActionType(), "resource should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteResource() {
        testResource = dao.getById(1);
        dao.delete(testResource);
        AdminAction deletedResource = dao.getById(testResource.getId());
        assertNull(deletedResource, "resource should be deleted");
    }

    /**
     * Get all check
     */
    @Test
    void testGetAllResources() {
        testResource = dao.getById(1);
        List<AdminAction> resourceList = dao.getAll();
        assertFalse(resourceList.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testResource = dao.getById(1);
        List<AdminAction> resourceList = dao.getByPropertyEqual("actionType", "Add Glaze");
        assertFalse(resourceList.isEmpty(), "should return at least one resource");
        assertEquals("Add Glaze", resourceList.get(0).getActionType(), "value should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testResource = dao.getById(1);
        List<AdminAction> resourceList = dao.getByPropertyLike("actionType", "Glaze");
        assertFalse(resourceList.isEmpty(), "should return resource with 'entry' in property");
        assertTrue(resourceList.get(0).getActionType().contains("Glaze"), "value should contain 'example'");
    }
}
