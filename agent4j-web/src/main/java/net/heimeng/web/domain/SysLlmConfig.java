package net.heimeng.web.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 大语言模型配置实体类 sys_llm_config
 *
 * @author InwardFlow
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_llm_config")
public class SysLlmConfig extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "llm_config_id")
    private Long llmConfigId;

    /**
     * 名称
     */
    private String nickName;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 配置key
     */
    private String configKey;

    /**
     * 秘钥
     */
    private String apiKey;

    /**
     * 访问站点
     */
    private String baseUrl;

    /**
     * LLM温度系数（创造力）
     */
    private Float temperature;

    /**
     * 是否启用（0=是,1=否）
     */
    private String status;

    /**
     * 扩展字段
     */
    private String params;

    /**
     * 备注
     */
    private String remark;

}
