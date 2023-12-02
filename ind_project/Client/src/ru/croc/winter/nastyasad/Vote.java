package ru.croc.winter.nastyasad;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Vote implements Serializable{
    private final LocalDateTime timestamp;
    private final int candidateId;
    @Serial
    private static final long serialVersionUID = 7343452969557482150L;


    public Vote(LocalDateTime timestamp, int candidateId) {
        this.timestamp = timestamp;
        this.candidateId = candidateId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Vote:" + "timestamp=" + timestamp +
                ", candidateId=" + candidateId;
    }
}
