package cn.jonny.mapper.User;

import cn.jonny.entity.User;

public interface UserMapper {
    public User selectUser(int id) throws Exception;
}
