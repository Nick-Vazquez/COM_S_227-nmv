public class Basketball
{

    private boolean isInflated;
    private final double diameter;

    public Basketball(double givenDiameter)
    {
        this.diameter = givenDiameter;
        isInflated = false;
    }

    public boolean isDribbleable()
    {
        return isInflated;
    }

    public double getDiameter()
    {
        return this.diameter;
    }

    public double getCircumference()
    {
        return this.diameter * Math.PI;
    }

    public void inflate()
    {
        this.isInflated = true;
    }
}