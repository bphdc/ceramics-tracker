package persistence;

import entity.AdminAction;
import entity.Project;
import entity.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import util.Database;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserDao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoTest {

    private static GenericDao<User> dao;
    private static SessionFactory sessionFactory;
    private User resource;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        dao = new GenericDao<>(User.class);
    }

    /**
     * create a test user before each test
     */
    @BeforeEach
    void setupEach() {
        Database database = Database.getInstance();
        //reset db
        database.runSQL("cleanDB.sql");

        //create one user using sql
        //database.runSQL("createUser.sql");
        database.runSQL("createTestResources.sql");


    }


    /**
     * Create check
     */
    @Test
    void testInsertUser() {
        //create data for test user
        resource = new User(0, "userObject", "testspec@example.com", "This is a bio",
                "profile.jpg", "passspec12314", "user", Timestamp.from(Instant.now()));

        //insert
        resource.setId(dao.insert(resource));
        assertNotEquals(0, resource.getId(), "id shouldn't be zero after insertion!");
        User insertedUser = dao.getById(resource.getId());
        assertNotNull(insertedUser, "new user should be retrievable");
        assertEquals("userObject", insertedUser.getUsername(), "username should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving user added by insertion
        resource = dao.getById(1);
        assertNotNull(resource, "user should be able to be found by id");
        assertEquals("testAdmin", resource.getUsername(), "username should match");
        assertEquals("testAdmin@example.com", resource.getEmail(), "email should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateUser() {
        resource = dao.getById(1);
        resource.setUsername("updateduser");
        dao.saveOrUpdate(resource);

        User updatedUser = dao.getById(resource.getId());
        assertNotNull(updatedUser, "updated user shouldn't be null");
        assertEquals("updateduser", updatedUser.getUsername(), "username should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteUser() {
        resource = dao.getById(1);
        dao.delete(resource);
        User deletedUser = dao.getById(resource.getId());
        assertNull(deletedUser, "user should be deleted");
    }

    /**
     * Get all users check
     */
    @Test
    void testGetAllUsers() {
        resource = dao.getById(1);
        List<User> users = dao.getAll();
        assertFalse(users.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        resource = dao.getById(1);
        List<User> users = dao.getByPropertyEqual("username", "testAdmin");
        assertFalse(users.isEmpty(), "should return at least one user");
        assertEquals("testAdmin", users.get(0).getUsername(), "username should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        resource = dao.getById(1);
        List<User> users = dao.getByPropertyLike("username", "test");
        assertFalse(users.isEmpty(), "should return users with 'test' in username");
        assertTrue(users.get(0).getUsername().contains("test"), "username should contain 'test'");
    }

    @Test
    void deleteWithAdminActions() {
        // get the user we want to delete that has an associated admin action
        User userToBeDeleted = dao.getById(1);
        List<AdminAction> adminActions = userToBeDeleted.getAdminActions();

        // get the associated admin action
        int adminActionId = adminActions.get(0).getId();

        // delete the user
        dao.delete(userToBeDeleted);

        // verify the user was deleted
        assertNull(dao.getById(1));

        // verify the admin action was also deleted
        GenericDao<AdminAction> adminActionsDao = new GenericDao<>(AdminAction.class);
        assertNull(adminActionsDao.getById(adminActionId));

    }

    @Test
    void deleteWithProjects() {
        // get the user we want to delete that has 2 orders associated
        User userToBeDeleted = dao.getById(1);
        List<Project> projects = userToBeDeleted.getProjects();

        // get the associated project id
        int prjId = projects.get(0).getProjectId();


        // delete the user
        dao.delete(userToBeDeleted);

        // verify the user was deleted
        assertNull(dao.getById(1));

        // verify the prject were also deleted
        GenericDao<Project> projectDao = new GenericDao<>(Project.class);
        assertNull(projectDao.getById(prjId));
    }
}
