package persistence;

import entity.*;
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
class TagDaoTest {

    private static GenericDao<Tag> dao;
    private static SessionFactory sessionFactory;
    private Tag testResource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(Tag.class);
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
        testResource = new Tag("test tag2", Timestamp.from(Instant.now()));

        //insert
        testResource.setTagId(dao.insert(testResource));
        assertEquals(2, testResource.getTagId(), "id should be 2 after insertion!");
        Tag insertedTag = dao.getById(testResource.getTagId());
        assertNotNull(insertedTag, "new resource should be retrievable");
        assertEquals("test tag2", insertedTag.getName(), "name should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving tag added by insertion
        testResource = dao.getById(1);
        assertNotNull(testResource, "resource should be able to be found by id");
        assertEquals("test tag", testResource.getName(), "name should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {
        testResource = dao.getById(1);
        testResource.setName("updated name");
        dao.saveOrUpdate(testResource);

        Tag updatedTag = dao.getById(testResource.getTagId());
        assertNotNull(updatedTag, "updated resource shouldn't be null");
        assertEquals("updated name", testResource.getName(), "resource should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteResource() {
        testResource = dao.getById(1);
        dao.delete(testResource);
        Tag deletedTag = dao.getById(testResource.getTagId());
        assertNull(deletedTag, "resource should be deleted");
    }

    @Test
    void deleteWithProjectLinkage() {
        // get the user we want to delete that has 2 orders associated
        Tag tagToBeDeleted = dao.getById(1);
        List<ProjectTag> projectTags = tagToBeDeleted.getProjects();

        // get the link entity
        ProjectTag projectTag = projectTags.get(0);

        //there's a value there
        assertNotNull(projectTag, "there should be a a project-glaze link");

        // delete the glz
        dao.delete(tagToBeDeleted);

        // verify the glaze was deleted
        assertNull(dao.getById(1));

        // verify the link was also deleted
        GenericDao<ProjectTag> prjTagDao = new GenericDao<>(ProjectTag.class);
        assert(prjTagDao.getAll().isEmpty());
    }

    /**
     * Get all check
     */
    @Test
    void testGetAllResources() {
        testResource = dao.getById(1);
        List<Tag> tags = dao.getAll();
        assertFalse(tags.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testResource = dao.getById(1);
        List<Tag> tags = dao.getByPropertyEqual("name", "test tag");
        assertFalse(tags.isEmpty(), "should return at least one resource");
        assertEquals("test tag", tags.get(0).getName(), "value should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testResource = dao.getById(1);
        List<Tag> tags = dao.getByPropertyLike("name", "test");
        assertFalse(tags.isEmpty(), "should return users with 'test' in resource");
        assertTrue(tags.get(0).getName().contains("test"), "value should contain 'test'");
    }
}
