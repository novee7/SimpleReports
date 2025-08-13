package dev.nove.model.managers;

public  enum ReportType {
    CHEATING,
    EXPLOITING,
    HARASSMENT,
    OTHER;

    public String path() {
        return "reports." + this.name().toLowerCase();
    }
}