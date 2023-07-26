package sh.miles.suketto.bukkit.task;

public enum TaskUnit {

    TICK(1),
    SECOND(20),
    MINUTE(1_200),
    HOUR(72_000),
    DAY(1_728_000),
    WEEK(12_096_000),
    MONTH(48_384_000),
    YEAR(580_608_000);

    private final long conversion;

    TaskUnit(long conversion) {
        this.conversion = conversion;
    }

    public long toTicks(long amount) {
        return conversion * amount;
    }
}
