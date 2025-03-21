package persistence;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * Generic dao inspired by the youtube video where paula talks about generic daos
 */

public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());


    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Get an entity by ID.
     *
     * @param id the entity ID
     * @return the entity
     */
    public T getById(int id) {
        Session session = getSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Save or update an entity.
     *
     * @param entity the entity to save or update
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a new entity.
     *
     * @param entity the entity to insert
     * @return the primary key of the entity
     */
    public int insert(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        int id = (Integer) session.getIdentifier(entity);
        session.close();
        return id;
    }

    /**
     * Delete an entity.
     *
     * @param entity the entity to delete
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get all entities of the given type.
     *
     * @return list of all entities
     */
    public List<T> getAll() {
        Session session = getSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root);
        List<T> entities = session.createSelectionQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Get entities by property (exact match).
     *
     * @param propertyName the property name
     * @param value        the property value
     * @return list of matching entities
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createSelectionQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Get entities by property (exact match).
     *
     * @param propertyName the property name
     * @param value        the property value as an object
     * @return list of matching entities
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {
        Session session = getSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createSelectionQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Get entities by property (like).
     *
     * @param propertyName the property name
     * @param value        the partial value to match
     * @return list of matching entities
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();
        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + value + "%"));
        List<T> entities = session.createSelectionQuery(query).getResultList();
        session.close();
        return entities;
    }



    /**
     * Returns an open session from session factory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}

