package sh.miles.suketto.bukkit.world;

import be.seeseemelk.mockbukkit.WorldMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.miles.suketto.bukkit.BaseTest;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest extends BaseTest {

    private Position position;

    @BeforeEach
    @Override
    public void setup() {
        super.setup();
        this.position = new Position(0, 0, 0);
    }

    @Test
    public void testPositionAdd() {
        position = position.add(1, 5, 7);

        assertEquals(position, new Position(1, 5, 7));
    }

    @Test
    public void testPositionSubtract() {
        position = position.subtract(1, 5, 7);

        assertEquals(position, new Position(-1, -5, -7));
    }

    @Test
    public void testPositionMultiply() {
        position = position.add(1, 1, 1).multiply(1, 5, 7);

        assertEquals(position, new Position(1, 5, 7));
    }

    @Test
    public void testPositionDivide() {
        position = position.add(2, 10, 14).divide(2, 2, 2);

        assertEquals(position, new Position(1, 5, 7));
    }

    @Test
    public void testGetLocation() {
        final WorldMock worldMock = getServer().addSimpleWorld("test");

        assertNotNull(position.location(worldMock));
    }

}
