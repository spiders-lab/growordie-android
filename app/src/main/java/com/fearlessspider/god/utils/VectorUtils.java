package com.fearlessspider.god.utils;

import android.graphics.PointF;

public class VectorUtils {

    final private static float EPSILON = 1E-4f;

    public static boolean isZero(float f){
        return Float.compare(Math.abs(f), EPSILON) < 0;
    }

    public static float vectorMagnitude(float[] vector){
        float accum = 0;

        for(int i = 0; i < vector.length; i++){
            accum += Math.pow(vector[i], 2);
        }

        return (float) java.lang.Math.sqrt(accum);
    }

    public static float vectorMagnitudeSquare(float[] vector){
        float accum = 0;

        for(int i = 0; i < vector.length; i++){
            accum += Math.pow(vector[i], 2);
        }

        return Math.abs(accum);
    }

    /*
     * Unit vector = vector / <magnitude of vector>
     */
    public static void convertToUnitVector(float[] vectorOut, float[] vectorIn){
        float magnitude = vectorMagnitude(vectorIn);
        for(int i = 0; i < vectorIn.length; i++){
            vectorOut[i] = vectorIn[i] / magnitude;
        }
    }

    /*
     * Unit vector = vector / <magnitude of vector>
     */
    public static float dotProduct(float[] vector1, float[] vector2){
        float sum = 0;
        for(int i = 0; i < vector1.length; i++){
            sum += vector1[i] * vector2[i];
        }

        return sum;
    }

    public static float[] vectorMult(float[] vector, float scalar)
    {
        float[] ret = new float[vector.length];
        for(int i = 0; i < vector.length; i++){
            ret[i] += vector[i] * scalar;
        }
        return ret;
    }

    public static float[] vectorSum(float[] vector, float[] vector2)
    {
        float[] ret = new float[vector.length];
        for(int i = 0; i < Math.min(vector.length, vector2.length); i++){
            ret[i] += vector[i] + vector2[i];
        }
        return ret;
    }

    public static float distance(PointF start, PointF end)
    {
        return (float)java.lang.Math.sqrt((float) (Math.pow(end.x - start.x, 2) +  Math.pow(end.y - start.y, 2)));
    }

    public static boolean areEqual(PointF p1, PointF p2){
        return VectorUtils.isZero(p1.x - p2.x) && VectorUtils.isZero(p1.y - p2.y);
    }

    public static boolean areEqual(float f1, float f2){
        return VectorUtils.isZero(f1 - f2);
    }

    public static float[] differentiate3Vector(float[] first, float[] second)
    {
        float[] ret = new float[3];

        for(int i = 0; i < 3; i++){
            ret[i] = second[i] - first[i];
        }

        return ret;
    }

    /**
     * Calculates the angle between two line segments. The two line segments start at the same point.
     * @return the angle, in radians, between the line segments [start, end1] and [start, end2]
     */
    public static float angleBetween(PointF start, PointF end1, PointF end2)
    {
        float ax = end1.x - start.x, ay = end1.y - start.y,
                bx = end2.x - start.x, by = end2.y - start.y;
        return (float)Math.atan2(ax*by - ay*bx, ax*bx + ay*by);
    }
}