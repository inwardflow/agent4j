package net.heimeng.web.test;

import net.heimeng.modules.ai.extractor.ExtractorUtils;
import net.heimeng.web.Agent4jWebApplication;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Slf4j
@SpringBootTest(classes = Agent4jWebApplication.class)
@DisplayName("AI 单元测试")
public class AiUnitTest {

    @Resource
    private ChatModel model;

    @Resource
    private ChatClient coldChatClient;

    @DisplayName("AI 配置项测试")
    @Test
    void propertiesTest() {
        // Given
        ChatClient client = ChatClient.builder(model).build();

        // Ensure the client is able to use
        log.debug(client.prompt().user("你好").call().content());

        // Result: 你好👋！我是人工智能助手智谱清言，可以叫我小智🤖，很高兴见到你，欢迎问我任何问题。
    }

    @DisplayName("Extractor 提取器测试")
    @Test
    void extractorTest() {
        // Given
        String text = "蔡徐坤（KUN），1998年8月2日出生于浙江省温州市，" +
                "户籍湖南省吉首市，中国内地男歌手、演员、原创音乐制作人、MV导演。" +
                "朱明春的长子朱立科浙农大毕业三年后，针对温州风俗——家逢喜事分红蛋的习惯，研发出了一鸣利市红蛋，" +
                "深受广大市民的喜爱，成为温州市民办喜事的必需品。之后，朱立科倡导的新型奶吧，" +
                "改变了部分温州人糯米饭加紫菜汤的早餐习惯。该模式一经推出大受温州人欢迎。" +
                "2002年在市区横渎开出第一家一鸣真鲜奶吧直营店，如今，一鸣真鲜奶吧直营店、加盟连锁店在温州地区已发展至近400家。";
        List<Person> persons = ExtractorUtils.extract(text, new ParameterizedTypeReference<>() {});
        log.debug(String.valueOf(persons));

        Person person = ExtractorUtils.extract(text, Person.class);
        log.debug(person.toString());

        // TODO: 实现 extract(text) 提取方法
        // person = ExtractorUtils.extract(text);
        record ActorFilms(String actor, List<String> movies) {}
        List<ActorFilms> actorFilms = coldChatClient.prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {});
        log.debug(actorFilms.toString());
    }
    @Data
    private static class Person {
        // 私有属性
        private String name;
        private int age;
        private String address;
        private List<String> occupations;
    }
}

