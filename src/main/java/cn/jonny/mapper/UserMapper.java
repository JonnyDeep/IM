package cn.jonny.mapper;

import cn.jonny.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
    User selectUser(int id);
    void inserUser(User user);
    void insertUserBash(List<User> userList);
}
