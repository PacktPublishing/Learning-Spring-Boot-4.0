package com.learningspringboot4;

import org.jspecify.annotations.Nullable;

record VideoV2(String name, @Nullable String description) {
}
