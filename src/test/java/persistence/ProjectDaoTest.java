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
 * Test class for projectdao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectDaoTest {

    private static GenericDao<Project> dao;
    private static SessionFactory sessionFactory;
    private Project testResource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(Project.class);
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
        testResource = new Project(0, userToAdd, "prj name", "here's a description", Timestamp.from(Instant.now()));

        //insert
        testResource.setProjectId(dao.insert(testResource));
        assertEquals(2, testResource.getProjectId(), "id should be 2 after insertion!");
        Project insertedResource = dao.getById(testResource.getProjectId());
        assertNotNull(insertedResource, "new resource should be retrievable");
        assertEquals("prj name", insertedResource.getName(), "value should match");
        //make sure user is there
        assertEquals(1, insertedResource.getUser().getId(), "user should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving tag added by insertion
        testResource = dao.getById(1);
        assertNotNull(testResource, "resource should be able to be found by id");
        assertEquals("Vase", testResource.getName(), "value should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {
        testResource = dao.getById(1);
        testResource.setName("updated name");
        dao.saveOrUpdate(testResource);

        Project updatedResource = dao.getById(testResource.getProjectId());
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
        Project deletedResource = dao.getById(testResource.getProjectId());
        assertNull(deletedResource, "resource should be deleted");
    }

    @Test
    void deleteWithEntries() {
        // get the user we want to delete that has an associated admin action
        Project projectToBeDeleted = dao.getById(1);
        List<ProjectEntry> projectEntries = projectToBeDeleted.getProjectEntries();

        // get the associated entry id
        int entryId = projectEntries.get(0).getId();

        // delete the pjl
        dao.delete(projectToBeDeleted);

        // verify the prj was deleted
        assertNull(dao.getById(1));

        // verify the entry was also deleted
        GenericDao<ProjectEntry> entryDao = new GenericDao<>(ProjectEntry.class);
        assertNull(entryDao.getById(entryId));

    }

    @Test
    void deleteWithImages() {
        // get the user we want to delete that has 2 orders associated
        Project prjToBeDeleted = dao.getById(1);
        List<Image> images = prjToBeDeleted.getImages();

        // get the associated img id
        int imgId = images.get(0).getImageId();

        //img id is 1
        assertEquals(1, imgId, "img id should be 1");


        // delete the prj
        dao.delete(prjToBeDeleted);

        // verify the prj was deleted
        assertNull(dao.getById(1));

        // verify the img was also deleted
        GenericDao<Image> imageDao = new GenericDao<>(Image.class);
        assertNull(imageDao.getById(imgId));
    }

    @Test
    void deleteWithGlazeLinkage() {
        // get the user we want to delete that has 2 orders associated
        Project prjToBeDeleted = dao.getById(1);
        List<ProjectGlaze> projectGlazes = prjToBeDeleted.getGlazes();

        // get the link entity
        ProjectGlaze projectGlaze = projectGlazes.get(0);

        //there's a value there
        assertNotNull(projectGlaze, "there should be a a project-glaze link");

        // delete the prj
        dao.delete(prjToBeDeleted);

        // verify the prj was deleted
        assertNull(dao.getById(1));

        // verify the link was also deleted
        GenericDao<ProjectGlaze> prjGlazeDao = new GenericDao<>(ProjectGlaze.class);
        assert(prjGlazeDao.getAll().isEmpty());
    }

    @Test
    void deleteWithTagLinkage() {
        // get the user we want to delete that has 2 orders associated
        Project prjToBeDeleted = dao.getById(1);
        List<ProjectTag> projectTags = prjToBeDeleted.getTags();

        // get the link entity
        ProjectTag projectTag = projectTags.get(0);

        //there's a value there
        assertNotNull(projectTag, "there should be a a project-tag link");

        // delete the prj
        dao.delete(prjToBeDeleted);

        // verify the prj was deleted
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
        List<Project> resourceList = dao.getAll();
        assertFalse(resourceList.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testResource = dao.getById(1);
        List<Project> resourceList = dao.getByPropertyEqual("name", "Vase");
        assertFalse(resourceList.isEmpty(), "should return at least one resource");
        assertEquals("Vase", resourceList.get(0).getName(), "value should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testResource = dao.getById(1);
        List<Project> resourceList = dao.getByPropertyLike("description", "thrown");
        assertFalse(resourceList.isEmpty(), "should return resource with 'thrown' in property");
        assertTrue(resourceList.get(0).getDescription().contains("thrown"), "value should contain 'thrown'");
    }
}
