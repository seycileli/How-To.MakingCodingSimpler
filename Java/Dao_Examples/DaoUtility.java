import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoUtility {
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("com.concretepage");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }

}
