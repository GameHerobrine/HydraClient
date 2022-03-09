package com.oldschoolminecraft.client.util;

import net.minecraft.client.Minecraft;

public class TextureLoadThread extends Thread
{
    private RemoteTexture tex;
    private Utils.GLTexturePipe pipe;

    public TextureLoadThread(RemoteTexture tex, Utils.GLTexturePipe pipe)
    {
        this.tex = tex;
        this.pipe = pipe;
    }

    public void run()
    {
        int textureID = Minecraft.getMinecraft().renderEngine.getTexture(tex.getURL());
        tex.setTextureID(textureID);
        pipe.pipe(tex);
    }
}
