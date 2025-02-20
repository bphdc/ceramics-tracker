package persistence;

import entity.User;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import util.Database;

/**
 * Test class for UserDao
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectDaoTest {

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
    void testInsertResource() {

    }

    /**
     * Read check
     */
    @Test
    void testGetById() {

    }

    /**
     * Update check
     */
    @Test
    void testUpdateResource() {

    }

    /**
     * Delete check
     */
    @Test
    void testDeleteResource() {

    }

    /**
     * Get all users check
     */
    @Test
    void testGetAllResources() {

    }

    /**
     * Check property method
     */
    @Test
    void testGetByPropertyEqual() {

    }

    /**
     * Check property like method
     */
    @Test
    void testGetByPropertyLike() {

    }
}
