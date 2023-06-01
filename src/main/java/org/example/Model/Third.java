package org.example.Model;

import org.example.Autowired;
import org.example.Component;

@Component
public class Third {
//    @Autowired // kind of circular problem
//    Third(Seventh seventh) {
//        System.out.println("Third");
//    }

    public Third() {
        System.out.println("Third");
    }
}
