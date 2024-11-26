package lk.ijse.bistroculinaryacademyorm.dao;

import lk.ijse.bistroculinaryacademyorm.dao.custom.impl.CourseDAOImpl;
import lk.ijse.bistroculinaryacademyorm.dao.custom.impl.StudentDAOImpl;
import lk.ijse.bistroculinaryacademyorm.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER,STUDENT,COURSE
    }

//    public SuperDAO getDAO(DAOTypes types){
//        switch (types) {
//            case USER:
//                return new UserDAOImpl();
//            case STUDENT:
//                return new StudentDAOImpl();
//            case COURSE:
//                return new CourseDAOImpl();
//            default:
//                return null;
//        }
//    }

    public <T extends SuperDAO> T getDao(DAOTypes types){
        switch (types) {
            case USER:
                return (T) new UserDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            default:
                return null;
        }
    }
}
