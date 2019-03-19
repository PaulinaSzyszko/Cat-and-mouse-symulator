package szyszko.paulina.board;

import szyszko.paulina.Cat;
import szyszko.paulina.Mouse;
import szyszko.paulina.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Point {

    private List<Player> players = new ArrayList<>();

    public boolean hasPlayer() {
        return !players.isEmpty();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void removePlayers(List players) {
        players.removeAll(players);
    }

    public boolean hasMice() {
        return !players
                .stream()
                .filter(player -> player instanceof Mouse)
                .collect(Collectors.toList())
                .isEmpty();
    }


    public boolean hasCat() {
        return !players
                .stream()
                .filter(player -> player instanceof Cat)
                .collect(Collectors.toList())
                .isEmpty();
    }

    public List<Mouse> getMice() {
        return players
                .stream()
                .filter(player -> player instanceof Mouse)
                .map(player -> (Mouse) player)
                .collect(Collectors.toList());
    }
    public List<Cat> getCat(){
        return players
                .stream()
                .filter(player -> player instanceof Cat)
                .map(player ->(Cat) player)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return players.toString();
    }
}
