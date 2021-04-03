package me.marcusslover.caverns.data;

import me.marcusslover.caverns.utils.IManager;

import java.util.ArrayList;
import java.util.List;

public class RankManager implements IManager<Rank> {
    private static RankManager instance;
    private final List<Rank> ranks;

    private RankManager() {
        instance = this;
        this.ranks = new ArrayList<>();
    }

    public static Rank get(String rank) {
        RankManager instance = getInstance();
        for (Rank rank1 : instance.getAll()) {
            if (rank1.name.equalsIgnoreCase(rank)) {
                return rank1;
            }
        }
        return get("player");
    }

    @Override
    public void initialize() {
        this.register(new Rank("dev", "%hex(#B96AC9)☽", "%hex(#B96AC9)"));
        this.register(new Rank("credits", "%hex(#17B890)⭐", "%hex(#17B890)"));
        this.register(new Rank("friend", "%hex(#9AE19D)✔", "%hex(#9AE19D)"));
        this.register(new Rank("player", "%hex(#FBF5F3)◇", "%hex(#FBF5F3)"));
    }

    @Override
    public void register(Rank value) {
        ranks.add(value);
    }

    @Override
    public List<Rank> getAll() {
        return ranks;
    }

    public static RankManager getInstance() {
        return instance == null ? new RankManager() : instance;
    }
}
