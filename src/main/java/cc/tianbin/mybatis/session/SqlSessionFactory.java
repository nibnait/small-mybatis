package cc.tianbin.mybatis.session;

/**
 * Created by nibnait on 2022/10/18
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
