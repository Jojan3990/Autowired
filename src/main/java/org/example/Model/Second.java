package org.example.Model;

import org.example.Autowired;
import org.example.Component;

@Component
public class Second {
    @Autowired
    public Second(Fourth fourth, Fifth fifth) {
        System.out.println("Second");
    }
}
