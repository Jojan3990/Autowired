package org.example.Model;

import org.example.Autowired;
import org.example.Component;

@Component
public class Fourth {

    @Autowired
    public Fourth(Sixth sixth) {
        System.out.println("Fourth");
    }
}
