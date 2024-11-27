package lk.ijse.bistroculinaryacademyorm.dao.custom;


import lk.ijse.bistroculinaryacademyorm.dao.CrudDAO;
import lk.ijse.bistroculinaryacademyorm.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    public User findUserById(String userId) throws Exception;
    public User findUserByname(String username) throws Exception;
    public boolean usernameExists(String username) throws SQLException, ClassNotFoundException;
}
