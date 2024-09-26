package net.heimeng.web.domain.vo;

import net.heimeng.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 大语言模型配置视图类
 *
 * @author InwardFlow
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysLlmConfigVO extends BaseEntity {

    /**
     * 主键
     */
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
