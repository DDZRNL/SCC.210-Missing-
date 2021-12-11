import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Clock;

public class Health {

    private Asset asset;
    private double x;
    private double y;
    private RectangleShape bar;
    private RectangleShape bar2;
    private Clock time;
    private int damage=1;

    public Health(Asset asset) {
        this.asset = asset;
        this.x = asset.xPosition;
        this.y = asset.yPosition;

        bar = new RectangleShape(new Vector2f(50, 6));
        bar.setPosition(new Vector2f((float)x, (float)y));
        bar.setFillColor(Color.GREEN);
        bar2 = new RectangleShape(new Vector2f(50, 6));
        bar2.setPosition(new Vector2f((float)x, (float)y));
        bar2.setFillColor(Color.RED);

        time = new Clock();

    }
    public Health(int x, int y, int width, int height, Asset a) {
        this.asset = a;
        bar = new RectangleShape(new Vector2f(width, height));
        bar.setPosition(new Vector2f((float)x, (float)y));
        bar.setFillColor(Color.GREEN);

        bar2 = new RectangleShape(new Vector2f(width, height));
        bar2.setPosition(new Vector2f((float)x, (float)y));
        bar2.setFillColor(Color.RED);

        time = new Clock();
    }

    public void draw(RenderWindow pane)
    {
        pane.draw(bar2);
        pane.draw(bar);
    }

    public void follow() {
        bar.setPosition(new Vector2f((float)asset.xPosition-190, (float)asset.yPosition-175));
        bar2.setPosition(new Vector2f((float)asset.xPosition-190, (float)asset.yPosition-175));
        if (asset.getHealth() > 0) {
            if (asset.isHit()) {
                if (time.getElapsedTime().asSeconds() >= 1) {
                    bar.setSize(new Vector2f(bar.getSize().x-(bar.getSize().x/asset.getHealth()), bar.getSize().y));
                    asset.reduceHealth(damage);
                    time.restart();
                }
            }
        }
        else {
            bar.setSize(new Vector2f(0, bar.getSize().y));
            asset.setHealth(0);
        }
    }

    public void update() {
        if (asset.getHealth() > 0) {
            if (asset.isHit()) {
                if (time.getElapsedTime().asSeconds() >= 1) {
                    bar.setSize(new Vector2f(bar.getSize().x-(bar.getSize().x/asset.getHealth()), bar.getSize().y));
                    asset.reduceHealth(damage);
                    time.restart();
                }
            }
        }
        else {
            bar.setSize(new Vector2f(0, bar.getSize().y));
            asset.setHealth(0);
        }
    }
    
    public void setDamage(int n) {
    	damage=n;
    }
}
