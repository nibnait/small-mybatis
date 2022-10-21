package cc.tianbin.mybatis.scripting;

import io.github.nibnait.common.utils.DataUtils;

public class ScriptingException extends RuntimeException {

    private final String message;

    public ScriptingException(Throwable cause) {
        this(cause, "null");
    }

    public ScriptingException(Throwable cause, String message) {
        super(message, cause);
        this.message = message;
    }

    public ScriptingException(Throwable cause, String format, Object... args) {
        super(DataUtils.format(format, args), cause);
        this.message = DataUtils.format(format, args);
    }

    public ScriptingException(String format, Object... args) {
        super(DataUtils.format(format, args));
        this.message = DataUtils.format(format, args);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
