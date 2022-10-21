package cc.tianbin.mybatis.scripting;

import java.util.HashMap;
import java.util.Map;

/**
 * 脚本语言注册器
 * Created by nibnait on 2022/10/21
 */
public class LanguageDriverRegistry {

    // map
    private final Map<Class<?>, LanguageDriver> LANGUAGE_DRIVER_MAP = new HashMap<Class<?>, LanguageDriver>();

    private Class<?> defaultDriverClass = null;

    public void register(Class<?> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("null is not a valid Language Driver");
        }
        if (!LanguageDriver.class.isAssignableFrom(cls)) {
            throw new ScriptingException("{} does not implements {}", cls.getName(), LanguageDriver.class.getName());
        }
        // 如果没注册过，再去注册
        LanguageDriver driver = LANGUAGE_DRIVER_MAP.get(cls);
        if (driver == null) {
            try {
                //单例模式，即一个Class只有一个对应的LanguageDriver
                driver = (LanguageDriver) cls.newInstance();
                LANGUAGE_DRIVER_MAP.put(cls, driver);
            } catch (Exception ex) {
                throw new ScriptingException(ex, "Failed to load language driver for {}", cls.getName());
            }
        }
    }

    public LanguageDriver getDriver(Class<?> cls) {
        return LANGUAGE_DRIVER_MAP.get(cls);
    }

    public LanguageDriver getDefaultDriver() {
        return getDriver(getDefaultDriverClass());
    }

    public Class<?> getDefaultDriverClass() {
        return defaultDriverClass;
    }

    // Configuration()有调用，默认的为XMLLanguageDriver
    public void setDefaultDriverClass(Class<?> defaultDriverClass) {
        register(defaultDriverClass);
        this.defaultDriverClass = defaultDriverClass;
    }

}
