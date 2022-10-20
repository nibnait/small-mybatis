package cc.tianbin.mybatis.executor;

import io.github.nibnait.common.utils.DataUtils;

public class ExecutorException extends RuntimeException {

    private final String message;

    public ExecutorException(Throwable cause) {
        this(cause, "null");
    }

    public ExecutorException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public ExecutorException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public ExecutorException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
