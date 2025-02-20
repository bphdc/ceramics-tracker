package persistence;

import entity.Image;
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
 * Test class for project entry dao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectEntryDaoTest {

    private static GenericDao<ProjectEntry> dao;
    private static SessionFactory sessionFactory;
    private ProjectEntry testResource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(ProjectEntry.class);
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
        testResource = new ProjectEntry(0, projectToAdd, "some entry text", Timestamp.from(Instant.now()));

        //insert
        testResource.setId(dao.insert(testResource));
        assertEquals(2, testResource.getId(), "id should be 2 after insertion!");
        ProjectEntry insertedResource = dao.getById(testResource.getId());
        assertNotNull(insertedResource, "new resource should be retrievable");
        assertEquals("some entry text", insertedResource.getEntryText(), "value should match");
        //make sure project is there
        assertEquals(1, insertedResource.getProject().getProjectId(), "project should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving tag added by insertion
        testResource = dao.getById(1);
        assertNotNull(testResource, "resource should be able to be found by id");
        assertEquals("Test entry", testResource.getEntryText(), "value should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {
        testResource = dao.getById(1);
        testResource.setEntryText("updated entry text");
        dao.saveOrUpdate(testResource);

        ProjectEntry updatedResource = dao.getById(testResource.getId());
        assertNotNull(updatedResource, "updated resource shouldn't be null");
        assertEquals("updated entry text", testResource.getEntryText(), "resource should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteResource() {
        testResource = dao.getById(1);
        dao.delete(testResource);
        ProjectEntry deletedResource = dao.getById(testResource.getId());
        assertNull(deletedResource, "resource should be deleted");
    }

    /**
     * Get all check
     */
    @Test
    void testGetAllResources() {
        testResource = dao.getById(1);
        List<ProjectEntry> resourceList = dao.getAll();
        assertFalse(resourceList.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testResource = dao.getById(1);
        List<ProjectEntry> resourceList = dao.getByPropertyEqual("entryText", "Test entry");
        assertFalse(resourceList.isEmpty(), "should return at least one resource");
        assertEquals("Test entry", resourceList.get(0).getEntryText(), "value should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testResource = dao.getById(1);
        List<ProjectEntry> resourceList = dao.getByPropertyLike("entryText", "entry");
        assertFalse(resourceList.isEmpty(), "should return resource with 'entry' in property");
        assertTrue(resourceList.get(0).getEntryText().contains("entry"), "value should contain 'entry'");
    }
}
