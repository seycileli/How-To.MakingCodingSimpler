//this is an example below of a whole Crud operation basically

public class User implements UserI {
    static EntityManager em = DaoUtility.getEntityManager();

    @Override
    public User getUser(User user) {
        /**
         * This is the User's profile
         * We will retrieve the User info via their user id
         */

        User user = null;

        user = em.find(User.class, user);

        em.close();
        DaoUtility.close();

        return user;
    }

    @Override
    public int insert(User user) {
        /**
         * Create a new user
         *
         * Using EntityTransaction is same as the same thing as
         * em.getTransaction().begin();
         *
         * using transaction(dot) is less code
         *
         */

        int result = 0;

        try {

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("Profile successfully created.");

            result = 1;

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();
            result = 0;

        } finally {

            em.close();
            DaoUtility.close();

        }
        return result; //1 = saved/ persisted, 0 unsuccessful
    }

    @Override
    public int update(User user) {

        /**
         * This will update the Users data
         *
         * The merge entity will update an existing entity by updating the entity's
         * properties
         *
         * in case of error, we will catch it with Exception and
         * print its message - printStackTrace
         * Then we will rollback/ which means we will undo the changes
         * that were made during the transaction
         */

        int result = 0;

        try {

            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            result = 1;

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();
            result = 0;

        } finally {

            em.close();
            DaoUtility.close();

        }

        return result;
    }

    @Override
    public int delete(int userid) {
        int result = 0;

        try {

            em.getTransaction().begin();
            User user = em.find(UserProfile.class, userid);
            em.remove(user);
            em.getTransaction().commit();
            result = 1;

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();
            result = 0;

        } finally {

            em.close();
            DaoUtility.close();
        }

        return result;
    }

    @Override
    public String getUserByEmail(String email) {
        em.getTransaction().begin();

        TypedQuery<UserProfile> tQ =
                em.createQuery("SELECT db FROM UserProfile db "
                        + "WHERE db.email=:email", User.class);

        tQ.setParameter("email", email);
        UserProfile userProfile = null;

        try {

            System.out.println("Retrieving User info");

            userProfile = tQ.getSingleResult();
            System.out.println(user.getEmail());

        } catch (Exception e) {

            e.printStackTrace();
            em.getTransaction().rollback();

        } finally {

            em.close();
            DaoUtility.close();
           
        }

        return user.getEmail();
    }

    @Override
    public boolean isUserValid(String email, String userPassword) {
        TypedQuery<User> typedQuery =
                em.createQuery("SELECT count(db.userid) FROM UserProfile db "
                        + "WHERE db.email = :email and db.password=:password", User.class);

        boolean result = false;

        typedQuery.setParameter("email", email);
        typedQuery.setParameter("password", userPassword);

        try {

            User user = typedQuery.getSingleResult();

            if (user != null) {
                result = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
            result = false;

        } finally {

            em.close();
            DaoUtility.close();

        }

        return result;
        //true or false will indicate if user exist or not in database
    }


}
