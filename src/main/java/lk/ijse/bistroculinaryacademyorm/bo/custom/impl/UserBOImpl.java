package lk.ijse.bistroculinaryacademyorm.bo.custom.impl;

import lk.ijse.bistroculinaryacademyorm.bo.custom.UserBO;
import lk.ijse.bistroculinaryacademyorm.config.SessionFactoryConfig;
import lk.ijse.bistroculinaryacademyorm.dao.DAOFactory;
import lk.ijse.bistroculinaryacademyorm.dao.custom.UserDAO;
import lk.ijse.bistroculinaryacademyorm.dto.UserDTO;
import lk.ijse.bistroculinaryacademyorm.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

//    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    UserDAO userDAO = DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO dto) throws IOException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userDAO.setSession(session);
            userDAO.add(new User(
                    dto.getUsername(),
                    dto.getEmail(),
                    dto.getPassword(),
                    dto.getConfirmPassword(),
                    dto.getJobrole()
            ));
            transaction.commit();
            return true;

        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            session.close();

        }

    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        Session session = SessionFactoryConfig.getInstance().getSession();

            userDAO.setSession(session);
            List<User> users = userDAO.getAll();
            ArrayList<UserDTO> userDTOS = new ArrayList<>();
            for (User u : users) {
                userDTOS.add(new UserDTO(
                        u.getUserName(),
                        u.getEmail(),
                        u.getUserPassword(),
                        u.getConfirmPassword(),
                        u.getJobRole()

                ));
            }
            session.close();
            return userDTOS;

    }
}
