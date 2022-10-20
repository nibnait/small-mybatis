package cc.tianbin.mybatis.session;

import io.github.nibnait.common.utils.DataUtils;

public class SqlSessionException extends RuntimeException {

    private final String message;

    public SqlSessionException(Throwable cause) {
        this(cause, "null");
    }

    public SqlSessionException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public SqlSessionException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public SqlSessionException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
