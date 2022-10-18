package cc.tianbin.mybatis.binding;

import io.github.nibnait.common.utils.DataUtils;

public class BindingException extends RuntimeException {

    private final String message;

    public BindingException(Throwable cause) {
        this(cause, "null");
    }

    public BindingException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public BindingException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public BindingException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
