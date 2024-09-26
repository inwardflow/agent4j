package net.heimeng.common.core.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * Validator 校验框架工具
 *
 * @author InwardFlow
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidatorUtils {

    private static final Validator VALID = SpringUtils.getBean(Validator.class);

    public static <T> T validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> validate = VALID.validate(object, groups);
        if (!validate.isEmpty()) {
            log.info("参数校验异常, 内容为: {}", JsonUtils.toJsonString(object));
            throw new ConstraintViolationException("参数校验异常", validate);
        }
        return object;
    }
}
