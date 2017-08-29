package edu.urfu.cadpp.web.dao;

import java.util.List;

import edu.urfu.cadpp.web.entity.User;

public interface UserDAO {

    void addUser(User user);

    User getUserByLogin(String login);

    User getUserById(long id);
    
    List<User> getUsersByPage(int pageid,int total);
    
    Long getUserPageCount(int usersPerPage);

    void updateUser(User user);

    List<User> getAllUsers(String login);

    List<User> getUsersByIdList(List<Long> idList);

    List<User> getUsersNotInIdList(List<Long> idList);
    
    public void deleteUserByLogin(String login);
}
