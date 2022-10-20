package cc.tianbin.mybatis.reflection;

import io.github.nibnait.common.utils.DataUtils;

public class ReflectionException extends RuntimeException {

    private final String message;

    public ReflectionException(Throwable cause) {
        this(cause, "null");
    }

    public ReflectionException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public ReflectionException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public ReflectionException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
