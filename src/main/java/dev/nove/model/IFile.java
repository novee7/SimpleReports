package dev.nove.model;

import java.util.List;
import java.util.UUID;

public interface IFile {
    void initialize();
    void addReport(Report report);
    List<Report> getReportsOfPlayer(UUID reporter);
    Report getLastReportOfPlayer(UUID reporter);
    Report getLastReport();
}
