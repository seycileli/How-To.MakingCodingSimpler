   public static void insert(UserDao userDao) {
        EntityManager em = DaoUtility.getEntityManager();

        try {                                    //don't be a lazy dev, use the try-catch, throws make your code ugly
            em.getTransaction().begin();                 //must begin before we can proceed
            em.persist(userDao);                       //insert user info to database
            em.getTransaction().commit();                       // save
        } catch (Exception e) {                            // catch the mother all exceptions     
            e.printStackTrace();                                 // print error msg
            em.getTransaction().rollback();                            //undo
        } finally {                                                //finally, amright?
            em.close();                                           //close entitymanager              
            DaoUtility.close();                                     //close factory via this class
            System.out.println("Profile successfully created.");          //print msg, optional
        }
    }
    
    /** I hope this was helpful :) */
