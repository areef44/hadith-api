package com.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "tbl_master_data",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_master_data_group_code",
        columnNames = {"group_id", "code"}
    )
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MasterData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "group_id", nullable = false)
    private MasterGroup group;

    @Column(length = 500, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String label;

    @Column(length = 255)
    private String description;

    @Column(name="sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name="is_active", nullable = false)
    private Boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MasterGroup getGroup() {
        return group;
    }

    public void setGroup(MasterGroup group) {
        this.group = group;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    

}
