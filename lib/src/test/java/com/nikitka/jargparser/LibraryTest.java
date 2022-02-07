/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.nikitka.jargparser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.nikitka.jargparser.annotation.Argument;
import com.nikitka.jargparser.config.BaseConfig;
import com.nikitka.jargparser.exception.NoDefaultConstructorException;

class LibraryTest {

    class TestConfig extends BaseConfig {
        private TestConfig(int i) {}
    }

    static class GoodConfig extends BaseConfig {
        @Argument
        public String test;
    }

    @Test 
    public void someLibraryMethodReturnsTrue() {
        assertThrows(NoDefaultConstructorException.class, () -> new ArgumentParser<TestConfig>(TestConfig.class));
        //assertEquals("No default contructor found in class", e.getMessage());
        //ArgumentParser parser = ArgumentParser.builder().setPrefix("--").setIgnoreUnknown(false).build();
        ArgumentParser<GoodConfig> parser = new ArgumentParser<>(GoodConfig.class);
        assertEquals("test", parser.parse(new String[]{"--test=test"}).test);
    }
}
