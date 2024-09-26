package net.heimeng.web.runner;

import net.heimeng.web.service.SysLlmConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author Lion Li
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SystemApplicationRunner implements ApplicationRunner {

    private final SysLlmConfigService sysModelConfigService;

    @Override
    public void run(ApplicationArguments args) {
        sysModelConfigService.init();
        log.info("初始化 LLM 配置成功");
    }

}
