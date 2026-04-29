/*
 * Minecraft Forge
 * Copyright (c) 2016-2018.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 2.1
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.minecraftforge.fml.relauncher;

import org.magmafoundation.magma.betterui.BetterUI;
import org.magmafoundation.magma.configuration.MagmaConfig;
import org.magmafoundation.magma.downloads.MagmaUpdater;
import org.magmafoundation.magma.libs.Dependencies;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.magmafoundation.magma.MagmaConstants.*;

public class ServerLaunchWrapper {

    public static long beginTime;
    public static List<String> LAUNCH_ARGS;

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        LAUNCH_ARGS = Arrays.stream(args).map(String::toLowerCase).collect(Collectors.toList());

        Path eula = Paths.get("eula.txt");

        if (LAUNCH_ARGS.contains("-noui")) {
            BetterUI.setEnabled(false);
            LAUNCH_ARGS.remove("-noui");
        }
        if (LAUNCH_ARGS.contains("-nologo")) {
            BetterUI.setEnableBigLogo(false);
            LAUNCH_ARGS.remove("-nologo");
        }
        if (LAUNCH_ARGS.contains("-accepteula")) {
            BetterUI.forceAcceptEULA(eula);
            LAUNCH_ARGS.remove("-accepteula");
        }

        BetterUI.printTitle(NAME, BRAND, System.getProperty("java.version") + " (" + System.getProperty("java.vendor") + ")", VERSION, BUKKIT_VERSION, FORGE_VERSION);

        if (getJavaVersion() > 8) {
            System.err.println("WARNING: You are running a version of Java that is not supported by Magma.\nPlease download the latest version of Java 8 (https://www.java.com/en/download/).");
            System.exit(-1);
        }

        if (!BetterUI.checkEula(eula))
            System.exit(0);

        Dependencies.start();

        // todo add back when we had one release
//        if (!LAUNCH_ARGS.contains("-dau")) {
//            System.out.println("Checking for updates...");
//            MagmaUpdater magmaUpdater = new MagmaUpdater();
//            if (magmaUpdater.versionChecker() && MagmaConfig.instance.magmaAutoUpdater.getValues()) {
//                magmaUpdater.downloadJar();
//            }
//        }

        new ServerLaunchWrapper().run(args);
    }

    public static int getJavaVersion() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2);
        }

        int dotPos = version.indexOf('.');
        int dashPos = version.indexOf('-');
        return Integer.parseInt(version.substring(0, dotPos > -1 ? dotPos : dashPos > -1 ? dashPos : 1));
    }

    private ServerLaunchWrapper() {

    }

    private void run(String[] args) {
        if (System.getProperty("log4j.configurationFile") == null) {
            // Set this early so we don't need to reconfigure later
            System.setProperty("log4j.configurationFile", "log4j2_magma.xml");
        }
        Class<?> launchwrapper = null;
        try {
            launchwrapper = Class.forName("net.minecraft.launchwrapper.Launch", true, getClass().getClassLoader());
            Class.forName("org.objectweb.asm.Type", true, getClass().getClassLoader());
        } catch (Exception e) {
            System.err.printf("We appear to be missing one or more essential library files.\n" +
                    "You will need to add them to your server before FML and Forge will run successfully.");

        }
        beginTime = System.nanoTime();

        try {
            Method main = launchwrapper.getMethod("main", String[].class);
            String[] allArgs = new String[args.length + 2];
            allArgs[0] = "--tweakClass";
            allArgs[1] = "net.minecraftforge.fml.common.launcher.FMLServerTweaker";
            System.arraycopy(args, 0, allArgs, 2, args.length);
            main.invoke(null, (Object) allArgs);
        } catch (Exception e) {
            System.err.printf("A problem occurred running the Server launcher.");
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

}
