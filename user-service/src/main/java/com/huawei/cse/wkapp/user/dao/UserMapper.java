package com.huawei.cse.wkapp.user.dao;

import com.huawei.cse.wkapp.user.api.UserInfo;

public interface UserMapper {
    void createUser(UserInfo userInfo);

    UserInfo getUserInfo(long userId);

    void updateUserInfo(UserInfo info);
    
    void clear();

    Double queryReduced();
}
