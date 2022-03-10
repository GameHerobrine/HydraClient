package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class ThreadCheckHasPaid extends Thread {
    // $FF: synthetic field
    final Minecraft field_28146_a;

    public ThreadCheckHasPaid(Minecraft var1) {
        this.field_28146_a = var1;
    }

    public void run() {
        System.out.println("We do a medium amount of trolling");
    }
}
