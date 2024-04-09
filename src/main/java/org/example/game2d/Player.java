package org.example.game2d;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class Player extends Entity {
    MoveState moveState = MoveState.DOWNSTAY;
    ImageView character;
    public Player(AnchorPane gameView){
        x = 600;
        y = 600;
        speed = 5;
        maxAnimationInterval = 5;
        animationInterval = 0;
        getPlayerImage();
        character = new ImageView(downStay);
        character.setFitHeight(height);
        character.setFitWidth(width);
        direction = Direction.DOWN;
        updatePlayerPos();
        gameView.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()){
                case W -> {
                    if(direction == Direction.UP)
                        animationInterval = ++animationInterval % maxAnimationInterval;
                    else{
                        direction = Direction.UP;
                        animationInterval = 0;
                    }
                    moveState = (moveState == MoveState.UP1 ? MoveState.UP2 : MoveState.UP1);
                    y -= speed;
                }
                case A -> {
                    if(direction == Direction.LEFT)
                        animationInterval = ++animationInterval % maxAnimationInterval;
                    else{
                        direction = Direction.LEFT;
                        animationInterval = 0;
                    }
                    moveState = (moveState == MoveState.LEFT1 ? MoveState.LEFT2 : MoveState.LEFT1);
                    x -= speed;
                }
                case S -> {
                    if(direction == Direction.DOWN)
                        animationInterval = ++animationInterval % maxAnimationInterval;
                    else{
                        direction = Direction.DOWN;
                        animationInterval = 0;
                    }
                    moveState = (moveState == MoveState.DOWN1 ? MoveState.DOWN2 : MoveState.DOWN1);
                    y += speed;
                }
                case D -> {
                    if(direction == Direction.RIGHT)
                        animationInterval = ++animationInterval % maxAnimationInterval;
                    else{
                        direction = Direction.RIGHT;
                        animationInterval = 0;
                    }
                    moveState = (moveState == MoveState.RIGHT1 ? MoveState.RIGHT2 : MoveState.RIGHT1);
                    x += speed;
                }
            }
            updatePlayerPos();
        });
        gameView.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()){
                case W -> moveState = MoveState.UPSTAY;
                case A -> moveState = MoveState.LEFTSTAY;
                case S -> moveState = MoveState.DOWNSTAY;
                case D -> moveState = MoveState.RIGHTSTAY;
            }
            animationInterval = 0;
            updatePlayerPos();
        });
    }
    public void getPlayerImage(){
        up1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerUp1.png")));
        up2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerUp2.png")));
        upStay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerUpStay.png")));
        right1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerRight1.png")));
        right2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerRight2.png")));
        rightStay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerRightStay.png")));
        down1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerDown1.png")));
        down2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerDown2.png")));
        downStay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerDownStay.png")));
        left1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerLeft1.png")));
        left2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerLeft2.png")));
        leftStay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("sprites/player/playerLeftStay.png")));
    }

    public void updatePlayerPos(){
        if(animationInterval == 0){
            switch (direction) {
                case UP -> character.setImage(moveState == MoveState.UP1 ? up1 : moveState == MoveState.UP2 ?
                        up2 : upStay);
                case RIGHT ->
                        character.setImage(moveState == MoveState.RIGHT1 ? right1 : moveState == MoveState.RIGHT2 ?
                                right2 : rightStay);
                case DOWN -> character.setImage(moveState == MoveState.DOWN1 ? down1 : moveState == MoveState.DOWN2 ?
                        down2 : downStay);
                case LEFT -> character.setImage(moveState == MoveState.LEFT1 ? left1 : moveState == MoveState.LEFT2 ?
                        left2 : leftStay);
            }
        }
        character.setLayoutX(x);
        character.setLayoutY(y);
    }
}
