<div align="center">
<img src="https://i.imgur.com/zTCTCWG.png" alt="Magma logo" align="middle"></img>

[![Forge](https://img.shields.io/badge/Minecraft%20Forge-1.12.2%20--%202860-orange.svg?style=flat)](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.12.2.html)
[![Spigot](https://img.shields.io/badge/Paper/Spigot-1.12.2-yellow.svg)](https://github.com/PaperMC/Paper/tree/ver/1.12.2)
![TeamCity build status](https://teamcity.magmafoundation.org/app/rest/builds/buildType:id:MagmaFoundation_Magma_Build/statusIcon.svg)
[![Discord](https://img.shields.io/discord/612695539729039411.svg?logo=discord&logoWidth=18&colorB=7289DA)](https://discord.gg/magma)

<a href="https://bstats.org/plugin/server-implementation/Magma/17219"> <img src="https://bstats.org/signatures/server-implementation/Magma.svg" alt="Stats" width="800"> </a>
</div>

## ❓ About 

Magma is the next generation of hybrid minecraft server softwares.

Magma is based on **Forge and Paper**, meaning it can run both **Craftbukkit/Spigot/Paper plugins and Forge mods**.

We hope to eliminate all issues with craftbukkit forge servers. In the end, we envision a seamless, low lag Magma experience with support for newer 1.12+ versions of Minecraft.
<details>
   <summary><b>Click here to see a screenshot!</b></summary>
   <img src="https://i.imgur.com/3DnRHur.png" alt="EssentialsX with Chisel and Biomes O' Plenty" width="640">
   </br><a href="https://essentialsx.net/">EssentialsX</a> with <a href="https://www.curseforge.com/minecraft/mc-mods/chisel">Chisel</a> and <a href="https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty">Biomes O' Plenty</a>
</details>

## ☕️ Java 8
Magma for 1.12 <b>requires Java 8</b>. Java 9 or higher do <b>not</b> work due to Forge 1.12.

If you need a higher version of Java for other applications, you can install both versions and use `"<path to java.exe>" -jar magma.jar` instead of `java -jar magma.jar`.

## 🌐 BungeeCord/Velocity

Magma is compatible with [Velocity](https://velocitypowered.com/downloads), but it does not support modern forwarding.

If you want to use Magma in a BungeeCord network, we recommend to use [Waterfall](https://github.com/PaperMC/Waterfall)/[Travertine](https://github.com/PaperMC/Travertine) or [HexaCord](https://github.com/HexagonMC/BungeeCord) instead since it has better Forge support than BungeeCord.

In order to use Magma with BungeeCord, you have to enable `forge-support` in the BungeeCord config, set `online-mode` to false in server.properties and `bungeecord` to true in spigot.yml.

## 🪣 Deployment

### Installation

1. Download the recommended builds from the [**Releases** section](https://github.com/magmafoundation/Magma-Forge/releases)
2. Make a new directory(folder) for the server
3. Move the jar that you downloaded into the new directory
4. Run the jar with your command prompt or terminal, going to your directory and entering `java -jar Magma-[version]-server.jar`. Change [version] to your Magma version number.

### Building the sources

- Clone the Project
  - You can use Git GUI (like GitHub Desktop/GitKraken) or clone using the terminal using:
    - `git clone https://github.com/magmafoundation/Magma-Forge`
  - Next, clone the submodules using:
    - `git submodule update --init --recursive`
- Building
  - First you want to run the build command
    - `./gradlew outputJar`
  - Now go and get a drink this may take some time
  - Navigate to `build/distributions` directory of the compiled source code
  - Copy the Jar to a new server directory (see Installation) or run `./gradlew launchServer`

### Plugin Development

- Clone the Project
  - You can use Git GUI (like GitHub Desktop/GitKraken) or clone using the terminal using:
    - `git clone https://github.com/magmafoundation/Magma-Forge`
  - Next, clone the submodules using:
    - `git submodule update --init --recursive`
- Building Jar
  - First you want to run the plugin gen command
    - `./gradlew genPluginJar`
  - Now go and get a drink this may take some time
  - Navigate to `build/distributions` directory
  - You should have a jar like `Magma-xxxxxx-plugin.jar`
  - Now create a plugin with that jar.

## ⚙️ Contributing

If you wish to inspect Magma, submit PRs, or otherwise work with Magma itself, you're in the right place!

Please read the [CONTRIBUTING.md](https://github.com/magmafoundation/Magma-Forge/CONTRIBUTING.md) to see how to contribute, setup, and run.

## 💬 Chat

You are welcome to visit Magma's Discord server [here](https://discord.gg/Magma) (recommended).

You could also go to Magma's subreddit [here](https://www.reddit.com/r/Magma).
