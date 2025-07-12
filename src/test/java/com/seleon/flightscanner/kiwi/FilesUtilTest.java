package com.seleon.flightscanner.kiwi;

import com.seleon.flightscanner.utils.FilesUtil;
import org.junit.jupiter.api.Test;

public class FilesUtilTest {

    @Test
    void loadSampleStringWithNTest() {
        final String skypickerPayload = "kiwi/hello_world_n.txt";

        String content = FilesUtil.load("src/test/resources/", skypickerPayload);
        System.out.println("File content:");
        String fixed = content.replace("\\n", "\n");

        System.out.println(fixed);
    }

    @Test
    void loadKiwiPayloadGraphQLStringWithNTest() {
        final String skypickerPayload = "kiwi/kiwi_payload_graphql_samle.txt";

        String content = FilesUtil.load("src/test/resources/", skypickerPayload);
        System.out.println("File content:");
        String fixed = content.replace("\\n", "\n");

        System.out.println(fixed);
    }

}