package org.a1aska17.controller;

import org.a1aska17.exception.ExistsAsARangeException;
import org.a1aska17.exception.LessThanZeroException;

import static org.a1aska17.constant.BookHubConstant.*;

public class InputValidator {
    public static int checkValueLessThanZero(int value) {
        if (value < 0) {
            throw new LessThanZeroException(LESS_THAN_ZERO);
        }
        return value;
    }

    public static int checkValueExistsAsRange(int value, int range) {
        if (value < 0 || value > range + 1) {
            throw new ExistsAsARangeException(EXISTS_AS_A_RANGE);
        }
        return value;
    }
}
