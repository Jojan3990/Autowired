package org.example.Model;

import org.example.Autowired;
import org.example.Component;

@Component
public class First {
    @Autowired
    public First(Second second, Third third) {
        System.out.println("First");
    }

    void check() {
        System.out.println("I am check");
    }

}
