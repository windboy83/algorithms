package com.charles.test;

import java.util.concurrent.Callable;

interface Reptile {
    ReptileEgg lay();
}

class FireDragon implements Reptile {
    public FireDragon() {
    }

    @Override
    public ReptileEgg lay() {
        return new ReptileEgg(() -> null).hatch().lay();
    }

    public static void main(String[] args) throws Exception {
        FireDragon fireDragon = new FireDragon();
        System.out.println(fireDragon instanceof Reptile);
    }
}

class ReptileEgg {
    public ReptileEgg(Callable<Reptile> createReptile) {
        try {
            createReptile.call().lay();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Reptile hatch() {
        return new FireDragon();
    }
}
