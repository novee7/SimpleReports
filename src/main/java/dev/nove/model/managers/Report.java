package dev.nove.model.managers;

import java.time.Instant;
import java.util.UUID;

public record Report(UUID reporter, UUID reported, ReportType type,String reason ,Instant timestamp) {
}