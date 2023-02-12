package Global;

import UI.IScene;
import java.io.IOException;

public class SceneManager {
    static IScene currentScene;

    public static void Next(IScene _scene)
            throws Exception
    {
        Clear();
        currentScene = _scene;
        _scene.Init();
    }

    static void Clear()
            throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
