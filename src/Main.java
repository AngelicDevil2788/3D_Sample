import obsidian.System.GraphicsManager._3DManager_.Coordinates.Camera;
import obsidian.System.GraphicsManager._3DManager_.Coordinates.Point3D;
import obsidian.System.GraphicsManager._3DManager_.Coordinates.PointConverter;
import obsidian.System.GraphicsManager._3DManager_.Coordinates.Polygon;
import obsidian.System.GraphicsManager._3DManager_._3DEngine_;
import obsidian.System.OBISystem;
import obsidian.System.WindowManager.Window;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    private static Window mainWindow;

    public static void onDestroy() {
        System.exit(0);
    }

    public static void main(String[] args) {


        OBISystem.graphics.window.createWindow(OBISystem.maxWidth, OBISystem.maxHeight, new Dimension(1, 1), "3D Game", new Color(0, 0, 0),  destroy -> onDestroy(), null, null);


        mainWindow = OBISystem.graphics.window.getWindowByName("3D Game");
        mainWindow.setCamera(new Camera(0, 0, 0));
        mainWindow.getCamera().setDirectionAngle(new Point3D(0, 0, 0));


        Polygon p = new Polygon(new Point3D(-100, -100, -200), new Point3D(100, -100, -200), new Point3D(100, 100, -200), new Point3D(-100, 100, -200));
        Polygon p2 = new Polygon(new Point3D(-100, -100, 10), new Point3D(100, -100, 10), new Point3D(100, 100, 10), new Point3D(-100, 100, 10));

        while(OBISystem.isOpen()) {
            OBISystem.graphics.window.refreshAllWindows();

            _3DEngine_.renderPolygon(p, mainWindow, new Color(255, 0, 0));
            _3DEngine_.renderPolygon(p2, mainWindow, new Color(0, 255, 0));

            OBISystem.graphics.window.updateAllWindows();
            if (mainWindow.getInput().getKey(KeyEvent.VK_SPACE)) {
                p2.rotate(true, (double)mainWindow.getCurrentFPS() / 1000, 0, 0, new Point3D(p2.getAverageX(), p2.getAverageY(), p2.getAverageZ()));
            }
            if (mainWindow.getInput().getKey(KeyEvent.VK_SHIFT)) {
                mainWindow.getCamera().setPos(new Point3D(mainWindow.getCamera().getPos().x, mainWindow.getCamera().getPos().y, mainWindow.getCamera().getPos().z + 10));
            }
        }
    }
}
