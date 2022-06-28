package com.parpiiev.time.model;

import javax.persistence.*;

@Table(name="activity_request")
@Entity
public class ActivityRequest {

    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer request_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false) // insertable = false, updatable = false
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable=false) // insertable = false, updatable = false
    private Activity activity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public ActivityRequest() {
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n ActivityRequest{" +
                "request_id=" + request_id +
                ", user=" + user +
                ", activity=" + activity +
                ", status=" + status +
                '}';
    }
}
