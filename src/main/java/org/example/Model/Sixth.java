package org.example.Model;

import org.example.Autowired;
import org.example.Component;

@Component
public class Sixth {
    @Autowired
    public Sixth() {
        System.out.println("Sixth");
    }
}
