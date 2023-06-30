package com.danjoh2019.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Highscore {
    private String highscoreList;
    // private final Map<Integer, List<Player>> difficultyMap = new HashMap<>();

    public Highscore() {
        try {
            Path path = Path.of("highscore/highscores");

            checkIfHighscoreFileExists(path);
            highscoreList = Files.readString(path);

        } catch (IOException ex) {
            System.err.println("Something went wrong with reading the highscore file");
        }
    }

    private void checkIfHighscoreFileExists(Path path) throws IOException {
        String scores = "0,----,";

        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        if (!Files.exists(path)) {
            StringBuilder newScores = new StringBuilder();

            newScores.append(scores.repeat(10));

            Files.writeString(path, newScores);
        }
    }

    private List<Player> generateListOfPlayers() {
        String[] highscore = highscoreList.split(",");
        List<Player> players = new ArrayList<>();

        for (int j = 0; j < 20; j += 2) {
            players.add(new Player(Integer.parseInt(highscore[j]), highscore[j + 1]));
        }

        return players;
    }

    public List<Player> getHighscore() {
        return generateListOfPlayers();
    }

    public boolean checkIfNewHighscore(Player player) {
        List<Player> players = generateListOfPlayers();

        for (Player p : players) {
            if (player.getScore() > p.getScore()) {
                return true;
            }
        }

        return false;
    }

    public void saveHighscore(Player player) {
        try {
            StringBuilder outputFile = new StringBuilder();

            List<String> newHighscoreList = new ArrayList<>();

            List<Player> players = generateListOfPlayers();
            players.add(player);

            players.sort(Collections.reverseOrder());

            StringBuilder highscores = new StringBuilder();

            for (int j = 0; j < 10; j++) {
                outputFile.append(players.get(j).getScore()).append(",").append(players.get(j).getName())
                        .append(",");
                highscores.append(players.get(j).getScore()).append(",").append(players.get(j).getName())
                        .append(",");
            }

            newHighscoreList.add(highscores.toString());
            highscoreList = highscores.toString();

            Files.writeString(Path.of("highscore/highscores"), outputFile);

        } catch (

        IOException ex) {
            System.err.println("Something went wrong with writing the highscore file");
        }
    }
}
