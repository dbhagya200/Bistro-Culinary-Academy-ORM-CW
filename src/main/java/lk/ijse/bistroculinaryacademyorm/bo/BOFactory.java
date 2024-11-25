package lk.ijse.bistroculinaryacademyorm.bo;

import lk.ijse.bistroculinaryacademyorm.bo.custom.impl.CourseBOImpl;
import lk.ijse.bistroculinaryacademyorm.bo.custom.impl.StudentBOImpl;
import lk.ijse.bistroculinaryacademyorm.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        USER,STUDENT,COURSE
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case COURSE:
                return new CourseBOImpl();
            default:
                return null;
        }
    }
}
