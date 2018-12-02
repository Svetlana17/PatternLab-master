package prototype;

import Classes.Pupil;

public class Prototype {
    private Pupil prototype;

    public Pupil createInstance() throws CloneNotSupportedException {
       return (Pupil) prototype.clone();

    }

    public void setPrototype(Pupil prototype) {
        this.prototype = prototype;
    }
}
