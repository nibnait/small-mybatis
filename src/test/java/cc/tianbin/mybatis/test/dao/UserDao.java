package cc.tianbin.mybatis.test.dao;

import cc.tianbin.mybatis.test.po.UserPO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by nibnait on 2022/05/30
 */
public interface UserDao {

    UserPO queryUserInfo(@Param("userId") Long userId);

    String queryUserName(@Param("userId") Long userId);

    Integer queryUserAge(@Param("userId") Long userId);

}
