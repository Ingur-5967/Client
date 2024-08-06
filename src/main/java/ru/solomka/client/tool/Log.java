package ru.solomka.client.tool;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

public class Log {

    public static void debug(Object ...args) {
        StackTraceElement trace = Objects.requireNonNull(getCurrentClass(), "Class not found");
        Arrays.stream(args).forEach(s -> {
            System.out.printf("\n\nClass: %s |:| Method: %s() |:| Line: %s\nDebug result: %s", trace.getFileName(), trace.getMethodName(), trace.getLineNumber(), s);
        });
    }

    @Nullable
    public static StackTraceElement getCurrentClass() {
        return Thread.currentThread().getStackTrace().length > 3 ? Thread.currentThread().getStackTrace()[3] : null;
    }


}