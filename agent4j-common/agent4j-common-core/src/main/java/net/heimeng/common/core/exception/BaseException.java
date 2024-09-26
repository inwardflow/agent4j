package net.heimeng.common.core.exception;

import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 基础异常
 *
 * @author InwardFlow
 */
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;
    public BaseException() {
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(String module) {
        this.module = module;
    }

    public BaseException(String module, String code, String message) {
        this.module = module;
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
