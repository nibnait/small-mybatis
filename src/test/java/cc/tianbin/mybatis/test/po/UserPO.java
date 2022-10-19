package cc.tianbin.mybatis.test.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by nibnait on 2022/05/30
 */
@Data
@NoArgsConstructor
public class UserPO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 0未知 1男 2女
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime ctime;

    /**
     * 更新时间
     */
    private LocalDateTime mtime;

}
