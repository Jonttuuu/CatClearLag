package me.time6628.clag.sponge;

import com.google.inject.Inject;
import me.time6628.clag.sponge.commands.*;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.world.Chunk;
import org.spongepowered.api.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by TimeTheCat on 7/2/2016.
 */
@Plugin(name = "CatClearLag", id = "catclearlag", version = "0.9.0", description = "A plugin to assist in removing lag from your server.")
public class CatClearLag {
	
    public static CatClearLag instance;

    private final Logger logger;

    @Inject
    public CatClearLag(Logger logger) {
        this.logger = logger;
        instance = this;
    }

    @Listener
    public void onInit(GameInitializationEvent event) {
        getLogger().info("Starting plugin...");
        registerCommands();
    }

    private void registerCommands() {
        getLogger().info("Registering commands...");
        Sponge.getCommandManager().register(this, ForceGCCommand.getCommand(), "forcegc", "forcegarbagecollection");
        Sponge.getCommandManager().register(this, UnloadChunksCommand.getCommand(), "unloadchunks", "uc");
    }

    public Logger getLogger() {
        return logger;
    }
    
    private List<Chunk> getChunks() {
        List<Chunk> chunks = new ArrayList<>();
        //get all worlds
        Collection<World> worlds = Sponge.getServer().getWorlds();
        worlds.forEach((temp) -> {
            //get all the entities in the world
            Collection<Chunk> tempLoadedChunks = (Collection<Chunk>) temp.getLoadedChunks();
            chunks.addAll(tempLoadedChunks);
        });
        return chunks;
    }

    public Integer unloadChunks() {
        final int[] i = {0};
        List<Chunk> chunks = getChunks();
        chunks.forEach(chunk -> {
            chunk.unloadChunk();
            i[0]++;
        });
        return i[0];
    }
    
}