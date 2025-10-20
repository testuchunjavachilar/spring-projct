package uz.salikhdev.springprojct.model;

public enum Status {
    TODO,
    IN_PROGRESS,
    COMPLETED;

    public static Status fromString(String value) {
        for (Status status : Status.values()) {
            if (status.name().equals(value)) {
                return status;
            }
        }
        throw new RuntimeException("Invalid status: " + value);
    }

}
