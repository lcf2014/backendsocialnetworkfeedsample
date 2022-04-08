package com.backendsocialnetworkapplication.controller;

import com.backendsocialnetworkapplication.entity.DoubleIdObjectEntity;
import com.backendsocialnetworkapplication.entity.PostEntity;
import com.backendsocialnetworkapplication.entity.QuoteEntity;
import com.backendsocialnetworkapplication.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostControllerIT {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostRepository postRepository;
    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");

        PostEntity postEntity1 = PostEntity.builder()
                .id("61cb456dcc98c61c22bc37d4")
                .userId("61cb456dcc98c61c22bc37d8")
                .content("Today I am feeling good.")
                .build();

        PostEntity postEntity2 = PostEntity.builder()
                .id("61cb456dcc98c61c22bc37d5")
                .userId("61cb456dcc98c61c22bc37d9")
                .content("Today I am not feeling good.")
                .build();
        postRepository.save(postEntity1);
        postRepository.save(postEntity2);
    }

    @Test
    public void shouldGetUserAllPostsSuccessfully() throws Exception {

        mvc.perform(get("/api/v1/posts/myposts")
                        .param("inputUserId", "61cb456dcc98c61c22bc37d9"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetUserFollowingPostsSuccessfully() throws Exception {

        mvc.perform(get("/api/v1/posts/followingposts")
                        .param("inputUserId", "61cb456dcc98c61c22bc37d9"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldPostSharePostSuccessfully() throws Exception {

        mvc.perform(post("/api/v1/posts/sharepost")
                .content(asJsonString(DoubleIdObjectEntity.builder().id1("61cb456dcc98c61c22bc37d9").id2("61cb456dcc98c61c22bc37d4").build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldPostQuotePostSuccessfully() throws Exception {

        mvc.perform(post("/api/v1/posts/quotepost")
                        .content(asJsonString(QuoteEntity.builder().doubleIdObjectEntity(DoubleIdObjectEntity.builder().id1("61cb456dcc98c61c22bc37d9").id2("61cb456dcc98c61c22bc37d4").build()).quoteMessage("Awesome Post!").build()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldInsertPostSuccessfully() throws Exception {

        PostEntity newPost =  PostEntity.builder()
                .id("61cb456dcc98c61c22bc37d3")
                .userId("61cb456dcc98c61c22bc37d9")
                .content("Today I am happy.")
                .build();

        mvc.perform(post("/api/v1/posts/insertpost")
                        .content(asJsonString(newPost)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

}