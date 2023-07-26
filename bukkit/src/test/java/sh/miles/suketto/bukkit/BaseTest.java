package sh.miles.suketto.bukkit;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.plugin.Plugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    private ServerMock server;
    private Plugin plugin;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
        plugin = MockBukkit.createMockPlugin("Suketto");
    }

    @AfterEach
    public void teardown() {
        MockBukkit.unmock();
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public ServerMock getServer() {
        return this.server;
    }
}
