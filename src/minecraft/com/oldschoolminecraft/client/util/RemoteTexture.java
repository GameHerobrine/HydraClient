package com.oldschoolminecraft.client.util;

public class RemoteTexture
{
    private String url;
    private int textureID = -1;

    public RemoteTexture(String url)
    {
        this.url = url;
    }

    public RemoteTexture setTextureID(int textureID)
    {
        this.textureID = textureID;
        return this;
    }

    public String getURL()
    {
        return url;
    }

    public int getTextureID()
    {
        return textureID;
    }
}
