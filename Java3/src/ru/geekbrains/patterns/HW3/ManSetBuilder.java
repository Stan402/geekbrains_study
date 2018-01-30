package ru.geekbrains.patterns.HW3;

import ru.geekbrains.patterns.HW3.entity.HatMan;
import ru.geekbrains.patterns.HW3.entity.ScarfMan;
import ru.geekbrains.patterns.HW3.entity.Set;

public class ManSetBuilder extends SetBuilder{

    @Override
    public void buildHat() {
        set.setHat(new HatMan());
    }

    @Override
    public void buildScarf() {
        set.setScarf(new ScarfMan());
    }

    @Override
    public Set getSet() {
        return set;
    }
}
