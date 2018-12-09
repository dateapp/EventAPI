package com.datingapp.event_api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

//    @Test
//    public void testLambdaFunctionHandler() {
//        LambdaFunctionHandler handler = new LambdaFunctionHandler();
//        Context ctx = createContext();
//
//        String output = handler.handleRequest(input, ctx);
//
//        // TODO: validate output here if needed.
//        Assert.assertEquals("Hello from Lambda!", output);
//    }
    
//    @Test
//    public void testYelpEventFunctionHandler() {
//    	YelpEventHandler handler = new YelpEventHandler();
//        Context ctx = createContext();
//
//        String output = handler.handleRequest("Qn10NEhycm3k5HQoueDYSQ", ctx);
//        System.out.println(output);
//        // TODO: validate output here if needed.
//       // Assert.assertEquals("Hello from Lambda!", output);
//    }
    
//    @Test
//    public void testEventSearchByIdFunctionHandler() {
//    	EventSearchByIdHandler handler = new EventSearchByIdHandler();
//        Context ctx = createContext();
//        Map<Object,Object> map = new HashMap<Object,Object>();
//        map.put("eventId", "42664432466");
//        map.put("eventSource", "EventBrite");
//        String output = handler.handleRequest(map, ctx);
//        System.out.println(output);
//        // TODO: validate output here if needed.
//       // Assert.assertEquals("Hello from Lambda!", output);
//    }
}
