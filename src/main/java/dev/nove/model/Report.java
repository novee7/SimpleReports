package dev.nove.model;

import java.time.Instant;
import java.util.UUID;

public record Report(UUID reporter, UUID reported, Report.Type type, String reason , Instant timestamp) {

    public  enum Type {
        CHEATING,
        EXPLOITING,
        HARASSMENT,
        OTHER;

        public String path() {
            return "reports." + this.name().toLowerCase();
        }
    }
}