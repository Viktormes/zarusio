package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    private int attackCoolDown = 0;
    private final int attackCoolDownPeriod = 22;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 45;
        solidArea.height = 60;


        attackArea.width = 80;
        attackArea.height = 90;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImages();
        setItems();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 23;
        // worldX = gp.tileSize * 17;
        // worldY = gp.tileSize * 17;
        // gp.currentMap = 1;
        defaultSpeed = 8;
        speed = defaultSpeed;
        direction = "down";

        level = 1;
        maxHealth = 6;
        currentHealth = maxHealth;
        maxMana = 1;
        currentMana = maxMana;
        strength = 1;
        dexterity = 1;
        intellect = 1;
        experience = 0;
        nextLevelExperience = getAmountExperienceToNextLevel();
        gold = 100;
        currentWeapon = new ObjectDefaultWhip(gp);
        currentRobe = new ObjectDefaultRobe(gp);
        projectile = new ObjectFrostBolt(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setDefaultPositions() {

        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 23;
        direction = "down";

    }

    public void restoreLifeAndMana() {
        currentHealth = maxHealth;
        currentMana = maxMana;
        invincible = false;
    }

    public void setItems() {

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentRobe);
        inventory.add(new ObjectKey(gp));
        inventory.add(new ObjectHealthPotion(gp));
        inventory.add(new ObjectAxe(gp));

    }

    public int getAttack() {
        return strength  * currentWeapon.attackValue;
    }

    public int getDefense() {
        return dexterity * currentRobe.defenseValue;
    }

    public int getAmountExperienceToNextLevel() {
        return level * 5;
    }

    public void getPlayerImage() {

        mainTitleScreenImage = setup("/res/player/mainTitleScreenImage", gp.tileSize, gp.tileSize);
        front1 = setup("/res/player/front1", gp.tileSize, gp.tileSize);
        front2 = setup("/res/player/front2", gp.tileSize, gp.tileSize);
        back1 = setup("/res/player/back1", gp.tileSize, gp.tileSize);
        back2 = setup("/res/player/back2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/right2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/left2", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImages() {

        if (currentWeapon.type == typeWhip) {
            attackBack1 = setup("/res/player/attackBack1", gp.tileSize, gp.tileSize * 2);
            attackBack2 = setup("/res/player/attackBack2", gp.tileSize, gp.tileSize * 2);
            attackFront1 = setup("/res/player/attackFront1", gp.tileSize, gp.tileSize * 2);
            attackFront2 = setup("/res/player/attackFront2", gp.tileSize, gp.tileSize * 2);
            attackRight1 = setup("/res/player/attackRight1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/res/player/attackRight2", gp.tileSize * 2, gp.tileSize);
            attackLeft1 = setup("/res/player/attackLeft1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/res/player/attackLeft2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == typeAxe) {
            attackBack1 = setup("/res/player/attackAxeBack1", gp.tileSize, gp.tileSize * 2);
            attackBack2 = setup("/res/player/attackAxeBack2", gp.tileSize, gp.tileSize * 2);
            attackFront1 = setup("/res/player/attackAxeFront1", gp.tileSize, gp.tileSize * 2);
            attackFront2 = setup("/res/player/attackAxeFront2", gp.tileSize, gp.tileSize * 2);
            attackRight1 = setup("/res/player/attackAxeRight1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/res/player/attackAxeRight2", gp.tileSize * 2, gp.tileSize);
            attackLeft1 = setup("/res/player/attackAxeLeft1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/res/player/attackAxeLeft2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void update() {


        if (!attacking) {
            attackCoolDown++;
        }

        if (attacking && (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            spriteCounter = 0;
            attacking = false;
        }

        if (keyH.spacePressed && !attacking && attackCoolDown >= attackCoolDownPeriod &&
                !(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            attackCoolDown = 0;
            attacking = true;
        }

        if (attacking) {
            attacking();

        } else {

            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                collisionOn = false;
                gp.collisionChecker.checkTile(this);

                int objIndex = gp.collisionChecker.checkObject(this, true);
                pickUpItem(objIndex);

                int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);

                contactEnemy(enemyIndex);

                gp.eventHandler.checkEvent();


                if (!collisionOn && !keyH.enterPressed) {

                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }

                }

                gp.keyH.enterPressed = false;

                spriteCounter++;
                if (spriteCounter > 4) {
                    if (spriteNumber == 1) {
                        spriteNumber = 2;
                    } else if (spriteNumber == 2) {
                        spriteNumber = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if (keyH.projectileKeyPressed && !projectile.alive
                    && shotAvailableCounter == 60 && projectile.hasResource(this)) {

                gp.playSound(15);
                projectile.set(worldX, worldY, direction, true, this);
                projectile.useResource(this);

                for(int i = 0; i < gp.projectile[1].length; i++) {
                    if(gp.projectile[gp.currentMap][i] == null) {
                        gp.projectile[gp.currentMap][i] = projectile;
                        break;
                    }
                }

                shotAvailableCounter = 0;
            }

            if(keyH.arrowKeyPressed && !projectile.alive && shotAvailableCounter == 60) {
                shootArrow();
                shotAvailableCounter = 0;
            }



            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shotAvailableCounter < 60) {
                shotAvailableCounter++;
            }

            if (currentHealth > maxHealth) {
                currentHealth = maxHealth;
            }
            if (currentMana > maxMana) {
                currentMana = maxMana;
            }

            if (currentHealth <= 0) {
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1;
                gp.stopMusic();
                gp.playSound(11);
            }
        }
    }

    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 20) {
            spriteNumber = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);

            int projectileIndex = gp.collisionChecker.checkEntity(this, gp.projectile);
            destroyProjectile(projectileIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

            damageEnemy(enemyIndex, attack, currentWeapon.knockBackPower);



        }
        if (spriteCounter == 5) {

            if(currentWeapon.type == typeWhip){
                gp.playSound(7);

            } else if (currentWeapon.type == typeAxe){
                gp.playSound(8);
            }

        }
        if (spriteCounter > 20) {
            spriteNumber = 1;
            attacking = false;
            spriteCounter = 0;
        }
    }


    public void contactEnemy(int enemyIndex) {

        if (enemyIndex != 999) {

            if (!invincible && !gp.enemy[gp.currentMap][enemyIndex].dying) {
                gp.playSound(4);

                int damage = gp.enemy[gp.currentMap][enemyIndex].attack - gp.player.defense;
                if (damage < 0) {
                    damage = 0;
                }
                gp.ui.addFloatingText(String.valueOf(damage), screenX + 40, screenY + 20, new Color(190, 8, 8));
                generateParticle(gp.player, gp.player);
                currentHealth -= damage;
                invincible = true;
            }
        }

    }
    public Color getParticleColor() {
        return new Color(190, 8, 8);
    }
    public int getParticleSize() {
        int size = 10;
        return size;
    }
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxHealth() {
        int maxHealth = 20;
        return maxHealth;
    }

    public void damageEnemy(int i, int attack, int knockBackPower) {

        if (i != 999) {
            if (!gp.enemy[gp.currentMap][i].invincible) {
                gp.playSound(3);

                if (knockBackPower > 0) {
                    knockBack(gp.enemy[gp.currentMap][i], knockBackPower);
                }

                int damage = attack - gp.enemy[gp.currentMap][i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                int oldHealth = gp.enemy[gp.currentMap][i].currentHealth;
                gp.enemy[gp.currentMap][i].currentHealth -= damage;
                if (gp.enemy[gp.currentMap][i].currentHealth < 0) {
                    gp.enemy[gp.currentMap][i].currentHealth = 0;
                }
                int actualDamage = oldHealth - gp.enemy[gp.currentMap][i].currentHealth;
                int enemyX = gp.enemy[gp.currentMap][i].worldX - gp.player.worldX + gp.player.screenX;
                int enemyY = gp.enemy[gp.currentMap][i].worldY - gp.player.worldY + gp.player.screenY;
                gp.ui.addFloatingText(String.valueOf(actualDamage), enemyX + 40, enemyY + 20, new Color(190, 8, 8));
                gp.enemy[gp.currentMap][i].invincible = true;
                gp.enemy[gp.currentMap][i].damageReaction();
                generateParticle(this, gp.enemy[gp.currentMap][i]);

                if (gp.enemy[gp.currentMap][i].currentHealth <= 0) {
                    gp.enemy[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("You killed a " + gp.enemy[gp.currentMap][i].name + "!");
                    gp.ui.addFloatingText(gp.enemy[gp.currentMap][i].experience + " EXP", screenX, screenY, Color.WHITE);
                    experience += gp.enemy[gp.currentMap][i].experience;
                    checkLevelUp();
                }
            }
        }

    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed) {
            if (i != 999) {
                gp.gameState = gp.dialogState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    public void pickUpItem(int i) {


        if (i != 999) {

            if (gp.itemObject[gp.currentMap][i].type == typePickUp) {

                gp.itemObject[gp.currentMap][i].use(this);
                gp.itemObject[gp.currentMap][i] = null;

            } else {
                String objectName = gp.itemObject[gp.currentMap][i].name;

                if (inventory.size() != maxInventorySize) {

                    if (objectName.equals("Super Socks")) {
                        gp.playSound(2);
                        speed += 10;
                        inventory.add(gp.itemObject[gp.currentMap][i]);
                        gp.itemObject[gp.currentMap][i] = null;
                        gp.ui.addMessage("You picked up the Super Socks! Wow you are fast!");

                    }
                    if (objectName.equals("Red Pear") && currentHealth != maxHealth) {
                        Random rand = new Random();
                        int healed = rand.nextInt(3) + 1;
                        int healthBeforeHeal = currentHealth;
                        currentHealth += healed;
                        if (currentHealth > maxHealth) {
                            currentHealth = maxHealth;
                        }
                        int actualHealed = currentHealth - healthBeforeHeal;
                        gp.itemObject[gp.currentMap][i] = null;
                        gp.ui.addFloatingText(String.valueOf(actualHealed), screenX + 40, screenY + 20, Color.GREEN);
                    }
                } else {
                    gp.ui.addMessage("Your inventory is full!");
                }
            }
        }
    }

    public void knockBack(Entity entity, int knockBackPower) {

        entity.direction = direction;
        entity.speed += knockBackPower;
        entity.knockBack = true;

    }

    public void destroyProjectile(int i) {

        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {

        if (experience >= nextLevelExperience) {
            level++;
            experience = 0;
            nextLevelExperience = getAmountExperienceToNextLevel();
            maxHealth += 2;
            maxMana += 1;
            currentHealth = maxHealth;
            currentMana = maxMana;
            strength++;
            dexterity++;
            intellect++;
            attack = getAttack();
            defense = getDefense();
            gp.playSound(5);
            gp.gameState = gp.dialogState;
            gp.ui.currentDialog = "You have reached level " + level + "!";

        }
    }


    public void checkLevelDown() {
        if (gp.gameState == gp.gameOverState) {
            experience = 0;
            nextLevelExperience = getAmountExperienceToNextLevel();
            currentHealth = maxHealth;
            currentMana = maxMana;
            attack = getAttack();
            defense = getDefense();
            gold = 0;
        }
    }

        public void selectItem () {

            int itemIndex = gp.ui.findItemIndex(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

            if (itemIndex < inventory.size()) {

                Entity selectedItem = inventory.get(itemIndex);
                if (selectedItem.type == typeWhip || selectedItem.type == typeAxe) {
                    currentWeapon = selectedItem;
                    attack = getAttack();
                    getPlayerAttackImages();
                    gp.ui.addMessage("You equipped the " + selectedItem.name + "!");
                }
                if (selectedItem.type == typeRobe) {
                    currentRobe = selectedItem;
                    defense = getDefense();
                    gp.ui.addMessage("You equipped the " + selectedItem.name + "!");
                }
                if (currentHealth != maxHealth) {
                    if (selectedItem.type == typeConsumable) {
                        selectedItem.use(this);
                        inventory.remove(itemIndex);
                    } else {
                        gp.ui.addMessage("");
                    }
                }
            }
        }

        public void shootArrow() {

            ObjectArrow arrow = new ObjectArrow(gp);
            gp.playSound(14);
            arrow.set(worldX, worldY, direction, true, this);


            for(int i = 0; i < gp.projectile[1].length; i++) {
                if(gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = arrow;
                    break;
                }
            }
        }


        public void draw (Graphics2D g2){

            BufferedImage image = null;

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up" -> {
                    if (!attacking) {
                        if (spriteNumber == 1) image = back1;
                        if (spriteNumber == 2) image = back2;
                    }

                    if (attacking) {
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNumber == 1) image = attackBack1;
                        if (spriteNumber == 2) image = attackBack2;
                    }

                }
                case "down" -> {
                    if (!attacking) {
                        if (spriteNumber == 1) image = front1;
                        if (spriteNumber == 2) image = front2;
                    }
                    if (attacking) {
                        if (spriteNumber == 1) image = attackFront1;
                        if (spriteNumber == 2) image = attackFront2;
                    }

                }
                case "left" -> {
                    if (!attacking) {
                        if (spriteNumber == 1) image = left1;
                        if (spriteNumber == 2) image = left2;
                    }

                    if (attacking) {
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNumber == 1) image = attackLeft1;
                        if (spriteNumber == 2) image = attackLeft2;
                    }
                }
                case "right" -> {
                    if (!attacking) {
                        if (spriteNumber == 1) image = right1;
                        if (spriteNumber == 2) image = right2;
                    }
                    if (attacking) {
                        if (spriteNumber == 1) image = attackRight1;
                        if (spriteNumber == 2) image = attackRight2;
                    }
                }
            }


            if (screenX > worldX) tempScreenX = worldX - (screenX - tempScreenX);
            if (screenY > worldY) tempScreenY = worldY - (screenY - tempScreenY);

            int rightOffset = gp.screenWidth - screenX;
            if (rightOffset > gp.worldWidth - worldX) {
                tempScreenX = (gp.screenWidth - (gp.worldWidth - worldX)) - (screenX - tempScreenX);
            }
            int bottomOffset = gp.screenHeight - screenY;
            if (bottomOffset > gp.worldHeight - worldY) {
                tempScreenY = (gp.screenHeight - (gp.worldHeight - worldY)) - (screenY - tempScreenY);
            }

            if (invincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            tempScreenX = screenX + solidArea.x;
            tempScreenY = screenY + solidArea.y;
            switch (direction) {
                case "up":
                    tempScreenY = screenY - attackArea.height;
                    break;
                case "down":
                    tempScreenY = screenY + gp.tileSize;
                    break;
                case "left":
                    tempScreenX = screenX - attackArea.width;
                    break;
                case "right":
                    tempScreenX = screenX + gp.tileSize;
                    break;
            }
            g2.setColor(Color.red);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
        }

    }
