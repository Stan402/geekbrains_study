package ru.geekbrains.patterns.HW3;

import ru.geekbrains.patterns.HW3.entity.Set;

public class SetDirector {
    public Set createSet(SetBuilder builder){
        builder.buildHat();
        builder.buildScarf();
        return builder.getSet();
    }
}
