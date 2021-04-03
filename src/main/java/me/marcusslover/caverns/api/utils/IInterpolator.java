package me.marcusslover.caverns.api.utils;

@FunctionalInterface
public interface IInterpolator {
    double[] interpolate(double from, double to, int max);
}
