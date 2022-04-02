# HydraClient
HydraClient is a Minecraft Beta 1.7.3 client aiming to...

# Development
This repo contains ONLY the src. You need to setup a [ModifactionStation's MCP b1.7.3 LTS workspace](https://github.com/ModificationStation/1.7.3-LTS). And replace the src folder.

# Features
TODO

# Credit
Rei for Minimap,
[BetaTweaks](https://github.com/rekadoodle/BetaTweaks) for server list

# Workspace setup (IMPORTANT)
 - Download and unzip the [ModifactionStation's MCP b1.7.3 LTS workspace](https://github.com/ModificationStation/1.7.3-LTS).
 - Open setup.bat/sh, write n if the setup asks you to install some modloader classes, and pick version b1.7.3.
 - Run decompile.bat.
 - After decompiling is done, replace the src folder with the one from the repo.
 - Open eclipse folder as a workspace in eclipse.
 - Click the arrow next to client.
 - Right-click src > go to properties > Resource Filters (Under Resource) and remove the "only matches net" filter.
 - Locate the /jars/bin/ folder and open minecraft.jar with 7-zip, WinRAR or preferred software and put every folder from assets to that jar.
 - Done!
