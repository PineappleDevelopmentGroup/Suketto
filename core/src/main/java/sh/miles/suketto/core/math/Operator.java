package sh.miles.suketto.core.math;

public enum Operator {

    ADD('+') {
        @Override
        public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    SUBTRACT('-') {
        @Override
        public double apply(double x1, double x2) {
            return x1 - x2;
        }
    },
    MULTIPLY('*') {
        @Override
        public double apply(double x1, double x2) {
            return x1 * x2;
        }
    },
    DIVIDE('/') {
        @Override
        public double apply(double x1, double x2) {
            return x1 / x2;
        }
    },
    MODULO('%') {
        @Override
        public double apply(double x1, double x2) {
            return x1 % x2;
        }
    };

    private final char character;

    Operator(final char character) {
        this.character = character;
    }

    public char getCharacter() {
        return this.character;
    }

    public abstract double apply(double x1, double x2);


}
