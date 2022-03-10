package com.oldschoolminecraft.client.packets;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GenericPacket extends Packet
{
    private String jsonData;

    public GenericPacket() {}

    public GenericPacket(String jsonData)
    {
        this.jsonData = jsonData;
    }

    public void readPacketData(DataInputStream dis) throws IOException
    {
        jsonData = dis.readUTF();
    }

    public void writePacketData(DataOutputStream dos) throws IOException
    {
        dos.writeUTF(jsonData);
    }

    public void processPacket(NetHandler netHandler)
    {
        netHandler.handlePerks(this);
    }

    public int getPacketSize()
    {
        return jsonData.length();
    }
}
