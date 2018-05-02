package ru.kpfu.itis.artgallery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.artgallery.config.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationIntegrationTest {

    @Test
    public void whenLoadApplication_thenSuccess() {
        Assert.assertTrue(true);
    }
}
