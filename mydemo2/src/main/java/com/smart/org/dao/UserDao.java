package com.smart.org.dao;


import com.smart.org.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dys on 2017/1/5.
 */
public interface UserDao {
    /**
     * @Param("caseName")强行注入Set,get方法
     * @param caseName
     * @return
     */
    public List<User> findUserInfo(@Param("caseName") String caseName);

    User getczcfinfo(Long id);
}
