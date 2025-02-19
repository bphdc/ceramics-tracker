package persistence;

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

    private static GenericDao<User> userDao;
    private static SessionFactory sessionFactory;
    private User testUser;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        userDao = new GenericDao<>(User.class);
        User testUser = new User();
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
        database.runSQL("createUser.sql");


    }


    /**
     * Create check
     */
    @Test
    void testInsertUser() {
        //create data for test user
        testUser = new User(0, "userObject", "testspec@example.com", "This is a bio",
                "profile.jpg", "passspec12314", "user", Timestamp.from(Instant.now()));

        //insert
        testUser.setId(userDao.insert(testUser));
        assertNotEquals(0, testUser.getId(), "id shouldn't be zero after insertion!");
        User insertedUser = userDao.getById(testUser.getId());
        assertNotNull(insertedUser, "new user should be retrievable");
        assertEquals("userObject", insertedUser.getUsername(), "username should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        //retrieving user added by insertion
        testUser = userDao.getById(1);
        assertNotNull(testUser, "user should be able to be found by id");
        assertEquals("testuser", testUser.getUsername(), "username should match");
        assertEquals("testuser@example.com", testUser.getEmail(), "email should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateUser() {
        testUser = userDao.getById(1);
        testUser.setUsername("updateduser");
        userDao.saveOrUpdate(testUser);

        User updatedUser = userDao.getById(testUser.getId());
        assertNotNull(updatedUser, "updated user shouldn't be null");
        assertEquals("updateduser", updatedUser.getUsername(), "username should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteUser() {
        testUser = userDao.getById(1);
        userDao.delete(testUser);
        User deletedUser = userDao.getById(testUser.getId());
        assertNull(deletedUser, "user should be deleted");
    }

    /**
     * Get all users check
     */
    @Test
    void testGetAllUsers() {
        testUser = userDao.getById(1);
        List<User> users = userDao.getAll();
        assertFalse(users.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        testUser = userDao.getById(1);
        List<User> users = userDao.getByPropertyEqual("username", "testuser");
        assertFalse(users.isEmpty(), "should return at least one user");
        assertEquals("testuser", users.get(0).getUsername(), "username should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        testUser = userDao.getById(1);
        List<User> users = userDao.getByPropertyLike("username", "test");
        assertFalse(users.isEmpty(), "should return users with 'test' in username");
        assertTrue(users.get(0).getUsername().contains("test"), "username should contain 'test'");
    }
}
