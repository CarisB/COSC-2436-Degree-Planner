package Global;

import View.IScene;
import java.io.IOException;

public class SceneManager {
    static IScene currentScene;
    static IScene nextScene;

    static void Clear()
            throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
