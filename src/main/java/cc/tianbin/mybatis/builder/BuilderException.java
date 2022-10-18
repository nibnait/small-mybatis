package cc.tianbin.mybatis.builder;

import io.github.nibnait.common.utils.DataUtils;

public class BuilderException extends RuntimeException {

    private final String message;

    public BuilderException(Throwable cause) {
        this(cause, "null");
    }

    public BuilderException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public BuilderException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public BuilderException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
