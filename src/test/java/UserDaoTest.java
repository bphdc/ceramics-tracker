import entity.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import persistence.UserDao;
import persistence.SessionFactoryProvider;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserDao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoTest {

    private static UserDao userDao;
    private static SessionFactory sessionFactory;
    private User testUser;

    /**
     * Set up prior to spec running
     */
    @BeforeAll
    static void setup() {
        sessionFactory = SessionFactoryProvider.getSessionFactory();
        userDao = new UserDao();
    }

    /**
     * create a test user before each test
     */
    @BeforeEach
    void setupEach() {
        testUser = new User(0, "testuser", "test@example.com", "This is a bio",
                "profile.jpg", "passwordhash12423", "user", Timestamp.from(Instant.now()));
        testUser.setId(userDao.insert(testUser));
    }

    /**
     * delete user after test
     */
    @AfterEach
    void cleanup() {
        User retrievedUser = userDao.getById(testUser.getId());
        if (retrievedUser != null) {
            userDao.delete(retrievedUser);
        }
    }

    /**
     * Create check
     */
    @Test
    void testInsertUser() {
        assertNotEquals(0, testUser.getId(), "id shouldn't be zero after insertion!");
        User insertedUser = userDao.getById(testUser.getId());
        assertNotNull(insertedUser, "new user should be retrievable");
        assertEquals("testuser", insertedUser.getUsername(), "username should match");
    }

    /**
     * Read check
     */
    @Test
    void testGetById() {
        User retrievedUser = userDao.getById(testUser.getId());
        assertNotNull(retrievedUser, "user should be able to be found by id");
        assertEquals("testuser", retrievedUser.getUsername(), "username should match");
        assertEquals("test@example.com", retrievedUser.getEmail(), "email should match");
    }

    /**
     * Update check
     */
    @Test
    void testUpdateUser() {
        testUser.setUsername("updateduser");
        userDao.update(testUser);

        User updatedUser = userDao.getById(testUser.getId());
        assertNotNull(updatedUser, "updated user shouldn't be null");
        assertEquals("updateduser", updatedUser.getUsername(), "username should be updated");
    }

    /**
     * Delete check
     */
    @Test
    void testDeleteUser() {
        userDao.delete(testUser);
        User deletedUser = userDao.getById(testUser.getId());
        assertNull(deletedUser, "user should be deleted");
    }

    /**
     * Get all users check
     */
    @Test
    void testGetAllUsers() {
        List<User> users = userDao.getAll();
        assertFalse(users.isEmpty(), "list shouldn't be empty");
    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {
        List<User> users = userDao.getByPropertyEqual("username", "testuser");
        assertFalse(users.isEmpty(), "should return at least one user");
        assertEquals("testuser", users.get(0).getUsername(), "username should match");
    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {
        List<User> users = userDao.getByPropertyLike("username", "test");
        assertFalse(users.isEmpty(), "should return users with 'test' in username");
        assertTrue(users.get(0).getUsername().contains("test"), "username should contain 'test'");
    }
}
