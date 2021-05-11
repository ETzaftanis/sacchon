package repository;

import org.hibernate.exception.ConstraintViolationException;
import org.restlet.engine.Engine;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Repository<T, K> {
    private EntityManager entityManager;

    public static final Logger LOGGER = Engine.getLogger(Repository.class);

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T save(T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            System.out.println("Already registered!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new T();
    }

    public abstract Class<T> getEntityClass();

    public abstract String getClassName();

    // Read select
    public T read(K id) {
        try {
            T t = entityManager.find(getEntityClass(), id);
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    //TODO add pagination int pagesize pageNumber / create another method
    public List<T> findAll() {
        try {
            return entityManager.createQuery("from " + getClassName()).getResultList();
        } catch (Exception e) {
            LOGGER.info("no record found" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<T> findAll(int pageSize, int pageNumber) {
        try {
            Query query = entityManager.createQuery("from " + getClassName());
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.info("no record found" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public T update(T t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //Verify

    public boolean delete(K id) {
        T t = read(id);
        if (t == null) {
            return false;
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}