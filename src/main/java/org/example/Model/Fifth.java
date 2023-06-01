package org.example.Model;

import org.example.Autowired;
import org.example.Component;

@Component
public class Fifth {

    @Autowired
    public Fifth(Seventh seventh) {
        System.out.println("Fifth");
    }
}
