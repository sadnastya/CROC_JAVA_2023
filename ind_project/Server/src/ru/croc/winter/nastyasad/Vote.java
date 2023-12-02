package ru.croc.winter.nastyasad;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Vote implements Serializable{
    @Serial
    private static final long serialVersionUID = 7343452969557482150L;

    private final LocalDateTime timestamp;
    private final int candidateId;

    protected Vote(LocalDateTime timestamp, int candidateId) {
        this.timestamp = timestamp;
        this.candidateId = candidateId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public Timestamp getTimestamp() {
        return Timestamp.valueOf(timestamp);
    }

    @Override
    public String toString() {
        return "Vote:" + "timestamp=" + Timestamp.valueOf(timestamp) +
                ", candidateId=" + candidateId;
    }
}

