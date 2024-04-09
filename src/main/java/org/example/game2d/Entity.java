package org.example.game2d;

import javafx.scene.image.Image;

public class Entity {
    public int height = GameApplication.finalTileSize;
    public int width = GameApplication.finalTileSize;
    public int x;
    public int y;
    public int speed;
    public Image up1, up2, upStay, right1, right2, rightStay, down1, down2, downStay, left1, left2, leftStay;
    public Direction direction;
    public int animationInterval;
    public int maxAnimationInterval;
}
