package com.example.springboot;

public class ExampleServices {
    public static void example(MyBean bodyIn) {
        bodyIn.setName( "Hello: Lord " + bodyIn.getName() );
        bodyIn.setId(bodyIn.getId() * 1000);
    }
}
