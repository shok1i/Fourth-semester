package com.shokii.kedwi;

public class GameItem {
    public int id;
    public String imgSrc, gameStatus, gameTitle;

    public GameItem(int id, String imgSrc, String gameStatus, String gameTitle) {
        this.id = id; this.imgSrc = imgSrc; this.gameStatus = gameStatus; this.gameTitle = gameTitle;
    }
}
