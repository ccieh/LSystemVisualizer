package lsv.core.graphics;

public interface GraphicsContext {
    void moveTo(double x, double y);
    void lineTo(double x, double y);
    void setLineWidth(double size);
    void setLineColor(double r, double g, double b);
    void stroke();
    void clear();
}
