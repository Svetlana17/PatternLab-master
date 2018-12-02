package observer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Face extends MouseAdapter implements Drawable {

    private final int width, height;
    private Mouth mouth;

    private Nose nose;
    private Eye[] eyes;

    Face(int width, int height) {
        this.height = height;
        this.width = width;

        eyes = new Eye[]{
                new Eye(width / 3, height / 3, width / 10, width / 14),
                new Eye(3 * width / 4, height / 3, width / 10, width / 14)
        };
        nose = new Nose(width / 2, height / 2, width / 8);
        mouth = new Mouth(width / 2, 3 * height / 4, width / 10);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawArc(0, 0, width, height, 0, 360);
        for (Eye eye : eyes) {
            eye.draw(g);
        }
        nose.draw(g);
        mouth.draw(g);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Eye eye : eyes) {
            eye.updateCoordinates(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Eye eye : eyes) {
            eye.checkClick(e.getX(), e.getY());
        }
        nose.checkClick(e.getX(), e.getY());
        mouth.checkClick(e.getX(), e.getY());
    }

    private static class Mouth implements Drawable {
        private final int centerX, centerY;
        private final int width;
        private boolean smiling;

        private final static double ZONE_DELTA = 3.0;

        private Mouth(int centerX, int centerY, int width) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.width = width;
        }

        private void checkClick(int pointerX, int pointerY) {
            if (clicked(pointerX, pointerY)) {
                smiling ^= true;
            }
        }

        private boolean clicked(int pointerX, int pointerY) {
            if (smiling) {
                int cx = centerX - width / 3 + width / 4;
                int cy = centerY - width / 3 + width / 4;
                int radius = width / 4;
                return pointerY * ZONE_DELTA > cy && Math.hypot(cx - pointerX, cy - pointerY) < radius * ZONE_DELTA;
            } else {
                return Math.abs(pointerY - centerY) < 50 && pointerX > centerX - width / 2 - 50 && pointerX < centerX + width / 2 + 50;
            }
        }

        @Override
        public void draw(Graphics g) {
            if (smiling) {
                g.drawArc(centerX - width / 3, centerY - width / 3, width / 2, width / 2, 180, 180);
            } else {
                g.drawLine(centerX - width / 2, centerY, centerX + width / 2, centerY);
            }
        }
    }
//рисуем нос
    private static class Nose implements Drawable {
        private final int centerX, centerY;
        private final int radius;
        private Color color = Color.yellow;

        private Nose(int centerX, int centerY, int radius) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;
        }
//меняем цвет носа
        private void checkClick(int pointerX, int pointerY) {
            if (Math.hypot(pointerX - centerX, pointerY - centerY) < radius) {
                color = color == Color.YELLOW ? Color.GREEN : Color.PINK;
            }
        }

        @Override
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 0, 360);
            g.setColor(Color.BLACK);
        }
    }

    private static class Eye implements Drawable {
        private final int centerX, centerY;
        private final int bigRadius, smallRadius;
        private int x, y;
        private boolean closed;

        private Eye(int centerX, int centerY, int bigRadius, int smallRadius) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.bigRadius = bigRadius;
            this.smallRadius = smallRadius;
            x = centerX;
            y = centerY;
        }

        private void updateCoordinates(int pointerX, int pointerY) {
            if (Math.hypot(pointerX - centerX, pointerY - centerY) < bigRadius - smallRadius) {
                x = pointerX;
                y = pointerY;
            } else {
                x = pointerX - centerX;
                y = pointerY - centerY;
                double len = Math.hypot(x, y);
                x = (int) Math.round(x / len * (bigRadius - smallRadius) + centerX);
                y = (int) Math.round(y / len * (bigRadius - smallRadius) + centerY);
            }
        }
//метод для закрытия глаза
        private void checkClick(int pointerX, int pointerY) {
            if (Math.hypot(pointerX - centerX, pointerY - centerY) < bigRadius) {
                closed ^= true;
            }
        }

        @Override
        public void draw(Graphics g) {
            if (g == null) {
                return;
            }
            //глаза
            g.drawArc(centerX - bigRadius, centerY - bigRadius, bigRadius * 2, bigRadius * 2, 0, 360);
            g.setColor(closed ? Color.GRAY : Color.WHITE);
            g.fillArc(centerX - bigRadius, centerY - bigRadius, bigRadius * 2, bigRadius * 2, 0, 360);
            g.setColor(Color.BLACK);

            if (!closed) {
                g.fillArc(x - smallRadius, y - smallRadius, smallRadius * 2, smallRadius * 2, 0, 360);
            }
        }
    }
}
