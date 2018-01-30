package ru.geekbrains.patterns.HW3;

import ru.geekbrains.patterns.HW3.entity.*;

public class WomanSetBuilder extends SetBuilder{

    @Override
    public void buildHat() {
        set.setHat(new HatWoman());
    }

    @Override
    public void buildScarf() {
        set.setScarf(new ScarfWoman());
    }

    @Override
    public Set getSet() {
        return set;
    }
}
