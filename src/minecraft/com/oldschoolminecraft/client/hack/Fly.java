package com.oldschoolminecraft.client.hack;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayerSP;
import org.lwjgl.input.Keyboard;

public class Fly {
    public static void runFly(EntityPlayerSP entityplayersp, Minecraft mc, boolean slow) {
        entityplayersp.onGround = false;
        entityplayersp.motionX = 0.0D;
        entityplayersp.motionY = 0.0D;
        entityplayersp.motionZ = 0.0D;
        double d1 = (double)(entityplayersp.rotationPitch + 90.0F);
        double d2 = (double)(entityplayersp.rotationYaw + 90.0F);
        boolean flag = Keyboard.isKeyDown(17) && mc.inGameHasFocus;
        boolean flag1 = Keyboard.isKeyDown(31) && mc.inGameHasFocus;
        boolean flag2 = Keyboard.isKeyDown(30) && mc.inGameHasFocus;
        boolean flag3 = Keyboard.isKeyDown(32) && mc.inGameHasFocus;
        if (flag) {
            if (flag2) {
                d2 -= 45.0D;
            } else if (flag3) {
                d2 += 45.0D;
            }
        } else if (flag1) {
            d2 += 180.0D;
            if (flag2) {
                d2 += 45.0D;
            } else if (flag3) {
                d2 -= 45.0D;
            }
        } else if (flag2) {
            d2 -= 90.0D;
        } else if (flag3) {
            d2 += 90.0D;
        }
        if (flag || flag2 || flag1 || flag3) {
            entityplayersp.motionX = Math.cos(Math.toRadians(d2));
            entityplayersp.motionZ = Math.sin(Math.toRadians(d2));
        }
        if (Keyboard.isKeyDown(57) && mc.inGameHasFocus) {
            entityplayersp.motionY += 3.0D;
        } else if (Keyboard.isKeyDown(42) && mc.inGameHasFocus) {
            entityplayersp.motionY -= 3.0D;
        } else if (Keyboard.isKeyDown(82) && mc.inGameHasFocus) {
            entityplayersp.motionY -= 6.0D;
        } else if (Keyboard.isKeyDown(77) && mc.inGameHasFocus) {
            entityplayersp.motionY += 6.0D;
        }

        if (slow) {
                entityplayersp.motionX /= 4.0D;
                entityplayersp.motionY /= 4.0D;
                entityplayersp.motionZ /= 4.0D;
        }

    }
}
