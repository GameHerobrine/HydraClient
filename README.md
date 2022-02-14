# OSMClientRewrite
A rewrite of the OldSchoolMinecraft Client.

# Development
This repo contains ONLY the src. You need to setup a [ModifactionStation's MCP b1.7.3 LTS workspace](https://github.com/ModificationStation/1.7.3-LTS). And replace the src folder.

# Workspace setup (IMPORTANT)
 - Download and unzip the [ModifactionStation's MCP b1.7.3 LTS workspace](https://github.com/ModificationStation/1.7.3-LTS).
 - Open setup.bat/sh, write n if the setup asks you to install some modloader classes, and pick version b1.7.3.
 - Run decompile.bat.
 - After decompiling is done, replace the src folder with the one from the repo.
 - Open eclipse folder as a workspace in eclipse.
 - Click the arrow next to client.
 - Right-click src > go to properties > Resource Filters (Under Resource) and remove the "only matches net" filter.
 - Done!

