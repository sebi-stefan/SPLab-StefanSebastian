package org.example.splabstefansebastian.lab3components;

import org.springframework.stereotype.Component;

@Component
public class ClientComponent {

    private final TransientComponent tc;
    private final SingletonComponent sc;

    public ClientComponent(SingletonComponent sc, TransientComponent tc) {
        this.sc = sc;
        this.tc = tc;
        System.out.println("ClientComponent::ClientComponent = " + this);
    }

    public void operation(){
        System.out.println("INvoked CLientComponent::operation() on " + this);
        System.out.println("    o SingletonComponent = " + sc);
        System.out.println("    o TransientComponent = " + tc);
    }
}
