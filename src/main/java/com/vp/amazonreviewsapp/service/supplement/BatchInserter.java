package com.vp.amazonreviewsapp.service.supplement;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BatchInserter<T> {
    CompletableFuture<List<T>> insertBatch(List<T> batch);
}
