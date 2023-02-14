package Global;

import View.IScene;
import java.io.IOException;

/*
Scene Manager uses a "next scene"->Init() system invoked by the main program loop
as opposed to directly invoking Init() on currentScene. This allows the deprecated Scene
to properly go out-of-scope for the GC instead of staying in memory and causing leaks.
Classes should call Next() to switch Scenes.
*/

public class SceneManager {
    static IScene currentScene;
    static IScene nextScene;

    static void Clear()
            throws IOException, InterruptedException
    {
        // Clears the cmd shell (Windows only)
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else {  // For other platforms
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static void Next(IScene _scene) { nextScene = _scene; }

    public static void Init()
            throws Exception
    {
        if (nextScene == null)
            throw new Exception("There is no Scene to load!");

        Clear();
        currentScene = nextScene;
        nextScene = null;
        currentScene.Init();
    }
}
