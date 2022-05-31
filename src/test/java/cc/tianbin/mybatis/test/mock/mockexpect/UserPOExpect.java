package cc.tianbin.mybatis.test.mock.mockexpect;

import cc.tianbin.mybatis.test.po.UserPO;

/**
 * Created by nibnait on 2022/10/17
 */
public class UserPOExpect {

    public static UserPO get() {
        UserPO userPO = new UserPO();
        userPO.setId(1L);
        userPO.setName("Tom");
        userPO.setAge(11);
        userPO.setGender((byte) 1);
        userPO.setAddress("上海");
        return userPO;
    }

}
