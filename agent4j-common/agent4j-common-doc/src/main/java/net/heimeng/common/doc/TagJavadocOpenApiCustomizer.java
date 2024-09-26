package net.heimeng.common.doc;

import net.heimeng.common.core.util.StringUtils;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Swagger 文档增强配置
 *
 * @author InwardFlow
 */
@Component
@Slf4j
public class TagJavadocOpenApiCustomizer implements GlobalOpenApiCustomizer {

    private static final Pattern SNAKE_CASE_PATTERN = Pattern.compile("^[a-zA-Z0-9-]+$");

    @Override
    public void customise(OpenAPI openApi) {
        if (openApi == null || openApi.getTags() == null || openApi.getPaths() == null) {
            log.debug("OpenAPI, tags or paths are null, skipping customization.");
            return;
        }

        List<Tag> tagsToCustomize = openApi.getTags().stream()
                .filter(tag -> SNAKE_CASE_PATTERN.matcher(tag.getName()).matches() && StringUtils.isNotEmpty(tag.getDescription()))
                .toList();

        tagsToCustomize.forEach(tag -> {
            String newName = tag.getDescription();
            openApi.getPaths().forEach((path, pathItem) -> {
                Map<String, Operation> operationsMap = Stream.of(
                                pathItem.getGet(),
                                pathItem.getDelete(),
                                pathItem.getOptions(),
                                pathItem.getPatch(),
                                pathItem.getPost(),
                                pathItem.getPut(),
                                pathItem.getTrace()
                        ).filter(operation -> operation != null && operation.getTags().contains(tag.getName()))
                        .collect(Collectors.toMap(Operation::getSummary, operation -> operation, (existing, replacement) -> existing));

                operationsMap.forEach((summary, operation) -> operation.getTags().set(operation.getTags().indexOf(tag.getName()), newName));
            });
            tag.setName(newName);
        });
    }
}
