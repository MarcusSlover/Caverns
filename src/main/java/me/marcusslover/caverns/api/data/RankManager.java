package me.marcusslover.caverns.api.data;

import me.marcusslover.caverns.api.utils.IManager;

import java.util.ArrayList;
import java.util.List;

public class RankManager implements IManager<Rank> {
    private static RankManager instance;
    private final List<Rank> ranks;

    private RankManager() {
        instance = this;
        this.ranks = new ArrayList<>();
        this.initialize();
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
        this.register(new Rank("dev", "%hex(#8149ba)☽", "%hex(#c994ff)"));
        this.register(new Rank("credits", "%hex(#00e1eb)⭐", "%hex(#8cfdff)"));
        this.register(new Rank("friend", "%hex(#b8f000)✔", "%hex(#dbff87)"));
        this.register(new Rank("player", "%hex(#dcf4f5)◇", "%hex(#dcf4f5)"));
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
