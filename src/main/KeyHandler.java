package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,
            enterPressed, spacePressed, cPressed, projectileKeyPressed,
            arrowKeyPressed, onePressed, twoPressed, threePressed;
    public boolean tPressed = false;


    public KeyHandler(GamePanel gp) {
        this.gp = gp;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {

            if (gp.ui.titleScreenState == 0) {

                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    gp.playSound(10);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    gp.playSound(10);
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) {
                        // Add later
                    }
                    if (gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            } else if (gp.ui.titleScreenState == 1) {

                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    gp.playSound(10);
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    gp.playSound(10);
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        System.out.println("Mage");
                        gp.gameState = gp.playState;
                        gp.playMusic(1);
                    }
                    if (gp.ui.commandNum == 1) {
                        System.out.println("Druid");
                        gp.gameState = gp.playState;
                        gp.playMusic(1);
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }

        if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_T) {
                tPressed = !tPressed;
            }

            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }

            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }

            if (code == KeyEvent.VK_C) {
                gp.gameState = gp.characterState;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.optionsState;
            }

            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_F){
                projectileKeyPressed = true;
            }
            if(code == KeyEvent.VK_G){
                arrowKeyPressed = true;
            }
            if(code == KeyEvent.VK_1){
                onePressed = true;
            }
            if (code == KeyEvent.VK_2) {
                twoPressed = true;
            }
            if(code == KeyEvent.VK_3){
                threePressed = true;
            }
        } else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        } else if (gp.gameState == gp.dialogState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        } else if (gp.gameState == gp.characterState) {
            if (code == KeyEvent.VK_C) {
                gp.gameState = gp.playState;
            }

            if(code == KeyEvent.VK_ENTER){
                gp.player.selectItem();
            }
            playerInventory(code);
        }
else if(gp.gameState == gp.optionsState){
            if(code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            int maxCommandNum = 0;
            switch(gp.ui.subState){
                case 0: maxCommandNum = 5;
                break;
                case 3: maxCommandNum = 1;
                break;
            }

            if ( code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSound(10);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = maxCommandNum;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSound(10);
                if (gp.ui.commandNum > maxCommandNum) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                if(gp.ui.subState == 0){
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                        gp.music.volumeScale--;
                        gp.music.checkVolume();
                        gp.playSound(10);
                    }
                    if(gp.ui.commandNum == 2 && gp.soundEffect.volumeScale > 0){
                        gp.soundEffect.volumeScale--;
                        gp.playSound(10);
                    }
                }
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                if(gp.ui.subState == 0){
                    if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                        gp.playSound(10);
                    }
                    if(gp.ui.commandNum == 2 && gp.soundEffect.volumeScale < 5){
                        gp.soundEffect.volumeScale++;
                        gp.playSound(10);
                    }
                }
            }

        }
        else if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
                gp.playSound(10);
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
                gp.playSound(10);
            }
            if (code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.retry();
                }
                else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                    gp.restart();
                }
            }

        }
        else if (gp.gameState == gp.tradeState) {
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (gp.ui.subState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;

                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                    gp.playSound(10);
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                    gp.playSound(10);
                }
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }

        if (gp.ui.subState == 2) {
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }

    public void playerInventory(int code) {

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSound(10);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSound(10);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSound(10);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSound(10);
            }
        }
    }

    public void npcInventory(int code) {

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSound(10);
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSound(10);
            }
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSound(10);
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSound(10);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_C) {
            cPressed = false;
        }
        if (code == KeyEvent.VK_F){
            projectileKeyPressed = false;
        }
        if(code == KeyEvent.VK_G){
            arrowKeyPressed = false;
        }
        if(code == KeyEvent.VK_1){
            onePressed = false;
        }
        if (code == KeyEvent.VK_2) {
            twoPressed = false;
        }
        if(code == KeyEvent.VK_3){
            threePressed = false;
        }
    }
}
