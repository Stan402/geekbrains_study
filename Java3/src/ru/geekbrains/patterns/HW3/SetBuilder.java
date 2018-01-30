package ru.geekbrains.patterns.HW3;

import ru.geekbrains.patterns.HW3.entity.Set;

public abstract class SetBuilder {
   protected Set set = new Set();

   public abstract void buildHat();
   public abstract void buildScarf();

   public abstract Set getSet();

}
