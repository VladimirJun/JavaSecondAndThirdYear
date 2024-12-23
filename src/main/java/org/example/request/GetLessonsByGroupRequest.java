package org.example.request;

import java.util.Objects;

public record GetLessonsByGroupRequest(
        String beginDate,
        String endDate,
        Long groupId
) {
    @Override
    public int hashCode() {
        return Objects.hash(beginDate, endDate, groupId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetLessonsByGroupRequest that)) return false;
        return Objects.equals(beginDate, that.beginDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(groupId, that.groupId);
    }

    @Override
    public String toString() {
        return "GetLessonsByGroupRequest{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}